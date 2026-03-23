package org.example.bibliotecaad.service;

import org.example.bibliotecaad.entity.Livro;
import org.example.bibliotecaad.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

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

    public String buscarLivroPorId(Integer id) {
        return livroRepository.findById(id)
                .map(Livro::getTitulo)
                .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado"));
    }
}
