package org.example.bibliotecaad.repository;

import org.example.bibliotecaad.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
