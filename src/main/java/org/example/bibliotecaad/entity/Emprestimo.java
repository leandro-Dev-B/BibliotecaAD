package org.example.bibliotecaad.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.bibliotecaad.entity.enums.StatusEmprestimo;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "emprestimos")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmprestimo;

    @Column(name = "id_usuario")
    private int idUsuario;

    @Column(name = "id_livro")
    private int idLivro;

    @Column(name = "data_emprestimo")
    private LocalDate dataEmprestimo;

    @Column(name = "data_devolucao_prevista")
    private LocalDate dataDevolucaoPrevista;

    @Column(name = "data_devolucao_real")
    private LocalDate dataDevolucaoReal;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_emprestimo")
    private StatusEmprestimo statusEmprestimo;
}
