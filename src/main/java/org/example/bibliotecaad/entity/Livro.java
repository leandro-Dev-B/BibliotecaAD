package org.example.bibliotecaad.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.bibliotecaad.entity.enums.GeneroLivro;
import org.example.bibliotecaad.entity.enums.SituacaoLivro;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLivro;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "autor")
    private String autor;

    @Column(name = "quantidade_total")
    private int quantidadeTotal;

    @Column(name = "quantidade_disponivel")
    private int quantidadeDisponivel;

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao")
    private SituacaoLivro situacao;

    @Column (name = "numero_de_paginas")
    private Integer numeroDePaginas;

    @Enumerated(EnumType.STRING)
    @Column (name = "genero")
    private GeneroLivro generoLivro;

    @Column (name = "ano_publicacao")
    private Integer anoPublicacao;

    @Column (name = "identificador")
    private String identificador;
}
