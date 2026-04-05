package org.example.bibliotecaad.service;

import org.example.bibliotecaad.dto.EmprestimoDto;
import org.example.bibliotecaad.entity.Emprestimo;
import org.example.bibliotecaad.entity.Livro;
import org.example.bibliotecaad.entity.Usuario;
import org.example.bibliotecaad.entity.enums.SituacaoLivro;
import org.example.bibliotecaad.entity.enums.StatusEmprestimo;
import org.example.bibliotecaad.mapping.EmprestimoMapper;
import org.example.bibliotecaad.repository.EmprestimoRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final EmprestimoMapper emprestimoMapper;
    private final LivroService livroService;
    private final UsuarioService usuarioService;

    public EmprestimoService(EmprestimoRepository emprestimoRepository, EmprestimoMapper emprestimoMapper, LivroService livroService, UsuarioService usuarioService) {
        this.emprestimoRepository = emprestimoRepository;
        this.emprestimoMapper = emprestimoMapper;
        this.livroService = livroService;
        this.usuarioService = usuarioService;
    }

    public List<Emprestimo> listarTodos() {
        return emprestimoRepository.findAll();
    }

    @Transactional
    public EmprestimoDto criarEmprestimo(Integer idUsuario, Integer idLivro) {
        verificacaoDeLivroEUsuario(idUsuario, idLivro);

        Optional<Usuario> usuarioOpt = usuarioService.buscarUsuarioPorId(idUsuario);
        Usuario usuario = usuarioOpt.orElseThrow(() -> new IllegalArgumentException("Usuário com ID " + idUsuario + " não encontrado"));

        if (usuarioOpt.get().isEmpAtivo()) {
            throw new IllegalArgumentException("Não será possível criar o empréstimo: usuário já possui um empréstimo ativo.");
        }

        Optional<Livro> livroOpt = livroService.buscarLivroPorId(idLivro);
        Livro livro = livroOpt.orElseThrow(() -> new IllegalArgumentException("Livro com ID " + idLivro + " não encontrado"));

        if (livro.getQuantidadeDisponivel() <= 0) {
            throw new IllegalArgumentException("Não será possível criar o empréstimo: livro não possui exemplares disponíveis.");
        }

        alteraStatusNaCriacaoDeEmprestimo(usuario,livro);

        Emprestimo emprestimo = emprestimoMapper.toEntity(idUsuario, idLivro);
        Emprestimo entity = emprestimoRepository.save(emprestimo);

        Optional<Usuario> nomeUsuario = usuarioService.buscarUsuarioPorId(idUsuario);
        Optional<Livro> tituloLivro = livroService.buscarLivroPorId(idLivro);

        return emprestimoMapper.emprestimoCriadoResposta(entity, nomeUsuario, tituloLivro);
    }

    public void deletarEmprestimo(Integer id) {
        emprestimoRepository.deleteById(id);
    }

    public Optional<Emprestimo> buscarEmprestimoPorId(Integer id) {
        return emprestimoRepository.findById(id);
    }

    public void verificacaoDeLivroEUsuario(Integer idUsuario, Integer idLivro) {
        usuarioService.validarExistenciaUsuario(idUsuario);
        livroService.validarExistenciaLivro(idLivro);
    }

    public void alteraStatusNaCriacaoDeEmprestimo(Usuario usuario, Livro livro) {
        usuario.setEmpAtivo(true);
        usuarioService.atualizarUsuario(usuario);

        livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() - 1);
        livro.setSituacao(SituacaoLivro.EMPRESTADO);
        livroService.atualizarLivro(livro);

    }

    public EmprestimoDto devolucaoDeEmprestimo (Integer idEmprestimo) {
        Optional<Emprestimo> emprestimoOpt = buscarEmprestimoPorId(idEmprestimo);
        Emprestimo emprestimo = emprestimoOpt.orElseThrow(() -> new IllegalArgumentException("Empréstimo com ID " + idEmprestimo + " não encontrado"));

        if (emprestimo.getStatusEmprestimo() == StatusEmprestimo.DEVOLVIDO) {
            throw new IllegalArgumentException("Não será possível registrar a devolução: empréstimo já está registrado como devolvido.");
        }

            Optional<Usuario> usuarioOpt = usuarioService.buscarUsuarioPorId(emprestimo.getIdUsuario());
            Usuario usuario = usuarioOpt.orElseThrow(() -> new IllegalArgumentException("Usuário com ID " + emprestimo.getIdUsuario() + " não encontrado"));

            Optional<Livro> livroOpt = livroService.buscarLivroPorId(emprestimo.getIdLivro());
            Livro livro = livroOpt.orElseThrow(() -> new IllegalArgumentException("Livro com ID " + emprestimo.getIdLivro() + " não encontrado"));

            Emprestimo emprestimoAtualizado = alteraStatusNaDevolucaoDeEmprestimo(usuario, livro, emprestimo);

            Optional<Usuario> nomeUsuario = usuarioService.buscarUsuarioPorId(emprestimoAtualizado.getIdUsuario());
            Optional<Livro> tituloLivro = livroService.buscarLivroPorId(emprestimoAtualizado.getIdLivro());

            return emprestimoMapper.emprestimoDevolvidoResposta(emprestimoAtualizado, nomeUsuario, tituloLivro);
    }

    public Emprestimo alteraStatusNaDevolucaoDeEmprestimo(Usuario usuario, Livro livro, Emprestimo emprestimo) {
        usuario.setEmpAtivo(false);
        usuarioService.atualizarUsuario(usuario);

        livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() + 1);
        livro.setSituacao(SituacaoLivro.DISPONIVEL);
        livroService.atualizarLivro(livro);

        emprestimo.setStatusEmprestimo(StatusEmprestimo.DEVOLVIDO);
        emprestimo.setDataDevolucaoReal(java.time.LocalDate.now());

        return emprestimoRepository.save(emprestimo);
    }

    @Scheduled(cron = "0 0 0 * * ?") // Executa diariamente à meia-noite
    public void verificarEmprestimosAtrasados() {
        List<Emprestimo> emprestimosAtivos = emprestimoRepository.findByStatusEmprestimo(StatusEmprestimo.ATIVO);
        java.time.LocalDate hoje = java.time.LocalDate.now();

        for (Emprestimo emprestimo : emprestimosAtivos) {
            if (emprestimo.getDataDevolucaoPrevista().isBefore(hoje)) {
                emprestimo.setStatusEmprestimo(StatusEmprestimo.ATRASADO);
                emprestimoRepository.save(emprestimo);
            }
        }
    }

}