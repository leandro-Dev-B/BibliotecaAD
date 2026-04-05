package org.example.bibliotecaad.mapping;

import org.example.bibliotecaad.dto.EmprestimoDto;
import org.example.bibliotecaad.entity.Emprestimo;
import org.example.bibliotecaad.entity.Livro;
import org.example.bibliotecaad.entity.Usuario;
import org.example.bibliotecaad.entity.enums.StatusEmprestimo;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class EmprestimoMapper {

    public Emprestimo toEntity(Integer idUsuario, Integer idLivro) {
        return Emprestimo.builder()
                .idUsuario(idUsuario)
                .idLivro(idLivro)
                .dataEmprestimo(LocalDate.now())
                .dataDevolucaoPrevista(LocalDate.now().plusDays(14))
                .statusEmprestimo(StatusEmprestimo.ATIVO)
                .build();
    }

    public EmprestimoDto emprestimoCriadoResposta(Emprestimo entity, Optional<Usuario> nomeUsuario, Optional<Livro> tituloLivro) {
        return EmprestimoDto.builder()
                .idEmprestimo(entity.getIdEmprestimo())
                .nomeUsuario(nomeUsuario.map(Usuario::getNome).orElse("Usuário Desconhecido"))
                .tituloLivro(tituloLivro.map(Livro::getTitulo).orElse("Livro Desconhecido"))
                .dataEmprestimo(entity.getDataEmprestimo())
                .dataDevolucaoPrevista(entity.getDataDevolucaoPrevista())
                .build();
    }

    public EmprestimoDto emprestimoDevolvidoResposta(Emprestimo entity, Optional<Usuario> nomeUsuario, Optional<Livro> tituloLivro) {
        return EmprestimoDto.builder()
                .idEmprestimo(entity.getIdEmprestimo())
                .nomeUsuario(nomeUsuario.map(Usuario::getNome).orElse("Usuário Desconecido"))
                .tituloLivro(tituloLivro.map(Livro::getTitulo).orElse("Livro Desconecido"))
                .dataEmprestimo(entity.getDataEmprestimo())
                .dataDevolucaoReal(LocalDate.now())
                .build();
    }
}
