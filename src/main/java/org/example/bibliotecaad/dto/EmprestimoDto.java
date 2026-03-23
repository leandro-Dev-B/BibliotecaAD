package org.example.bibliotecaad.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmprestimoDto {
    private Integer idEmprestimo;
    private String nomeUsuario;
    private String tituloLivro;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataEmprestimo;
}
