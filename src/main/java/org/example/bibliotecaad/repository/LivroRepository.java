package org.example.bibliotecaad.repository;

import org.example.bibliotecaad.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {
    boolean existsByIdentificador(String identificador);
}
