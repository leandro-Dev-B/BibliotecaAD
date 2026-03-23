package org.example.bibliotecaad.controller;

import org.example.bibliotecaad.entity.Livro;
import org.example.bibliotecaad.service.LivroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

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
