package org.example.bibliotecaad.service;

import org.example.bibliotecaad.dto.EmprestimoDto;
import org.example.bibliotecaad.entity.Emprestimo;
import org.example.bibliotecaad.entity.Livro;
import org.example.bibliotecaad.entity.Usuario;
import org.example.bibliotecaad.mapping.EmprestimoMapper;
import org.example.bibliotecaad.repository.EmprestimoRepository;
import org.springframework.stereotype.Service;

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

    public EmprestimoDto criarEmprestimo(Integer idUsuario, Integer idLivro) {
        usuarioService.validarExistenciaUsuario(idUsuario);
        livroService.validarExistenciaLivro(idLivro);

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
}