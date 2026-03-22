package org.example.bibliotecaad.controller;

import org.example.bibliotecaad.model.Livro;
import org.example.bibliotecaad.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public List<Livro> listarTodos() {
        return livroService.listarTodos();
    }

    @PostMapping
    public Livro criarLivro(Livro livro) {
        return livroService.criarLivro(livro);
    }

    @PutMapping
    public Livro atualizarLivro(Livro livro) {
        return livroService.atualizarLivro(livro);
    }

    @DeleteMapping
    public void deletarLivro(Integer id) {
        livroService.deletarLivro(id);
    }

}
