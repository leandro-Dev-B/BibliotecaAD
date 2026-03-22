package org.example.bibliotecaad.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.bibliotecaad.model.enums.NivelDeAcesso;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(name = "nome", length = 35)
    private String nome;

    @Column(name = "idade")
    private int idade;

    @Column(name = "senha")
    private String senha;

    @Column(name = "ativo")
    private boolean ativo;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_de_acesso")
    private NivelDeAcesso nivelDeAcesso;

    @Column (name = "email")
    private String email;

    @Column (name = "telefone")
    private String telefone;

    @Column (name = "data_cadastro")
    private LocalDate dataCadastro;

    @Column (name = "data_nascimento")
    private LocalDate dataDeNascimento;
}
