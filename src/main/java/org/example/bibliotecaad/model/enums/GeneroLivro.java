package org.example.bibliotecaad.model.enums;

import lombok.Getter;

@Getter
public enum GeneroLivro {

    TEOLOGIA("Teologia"),
    DEVOCIONAL("Devocional"),
    ESTUDO_BIBLICO("Estudo Bíblico"),
    VIDA_CRISTA("Vida Cristã"),
    FAMILIA("Família"),
    RELACIONAMENTO("Relacionamento"),
    LIDERANCA("Liderança Cristã"),
    PREGACAO("Pregação"),
    HISTORIA("História"),
    BIOGRAFIA("Biografia"),
    INFANTIL("Infantil"),
    JUVENIL("Juvenil"),
    FICCAO("Ficção"),
    SAUDE_MENTAL("Saúde Mental");

    private String descricao;

    GeneroLivro(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}