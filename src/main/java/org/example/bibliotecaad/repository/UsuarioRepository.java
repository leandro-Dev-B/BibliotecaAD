package org.example.bibliotecaad.repository;

import org.example.bibliotecaad.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
