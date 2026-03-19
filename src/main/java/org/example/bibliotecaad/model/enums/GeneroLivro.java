package org.example.bibliotecaad.model.enums;

import lombok.Getter;

@Getter
public enum GeneroLivro {

    TEOLOGIA("Teologia"),
    DEVOCIONAL("Devocional"),
    ESTUDO_BIBLICO("Estudo Bíblico"),
    VIDA_CRISTA("Vida Cristã"),
    FAMILIA("Família e Relacionamentos"),
    LIDERANCA("Liderança Cristã"),
    PREGACAO("Pregação"),
    HISTORIA_DA_IGREJA("História da Igreja"),
    BIOGRAFIA("Biografia"),
    INFANTIL("Infantil/Juvenil");

    private String descricao;

    GeneroLivro(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}