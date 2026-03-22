package org.example.bibliotecaad.service;

import org.example.bibliotecaad.model.Livro;
import org.example.bibliotecaad.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }

    public Livro criarLivro(Livro livro) {
        if (livro.getIdentificador() == null || livro.getIdentificador().isEmpty()) {
            throw new IllegalArgumentException("O identificador do livro é obrigatório.");
        }
        if (livroRepository.existsByIdentificador(livro.getIdentificador())) {
            throw new IllegalArgumentException("Já existe um livro com este identificador.");
        }
        return livroRepository.save(livro);
    }

    public Livro atualizarLivro(Livro livro) {

        if (!livroRepository.existsById(livro.getIdLivro())) {
            throw new IllegalArgumentException("Livro não encontrado para atualização.");
        }
        return livroRepository.save(livro);
    }

    public void deletarLivro(Integer id) {
        if (!livroRepository.existsById(id)) {
            throw new IllegalArgumentException("Livro não encontrado para exclusão.");
        }
        livroRepository.deleteById(id);
    }
}
