package org.example.bibliotecaad.mapping;

import org.example.bibliotecaad.dto.EmprestimoDto;
import org.example.bibliotecaad.entity.Emprestimo;
import org.example.bibliotecaad.entity.Usuario;
import org.example.bibliotecaad.entity.enums.StatusEmprestimo;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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

    public EmprestimoDto emprestimoCriadoResposta(Emprestimo entity, String nomeUsuario, String tituloLivro) {
        return EmprestimoDto.builder()
                .idEmprestimo(entity.getIdEmprestimo())
                .nomeUsuario(nomeUsuario)
                .tituloLivro(tituloLivro)
                .dataEmprestimo(entity.getDataEmprestimo())
                .dataDevolucaoPrevista(entity.getDataDevolucaoPrevista())
                .build();
    }
}
