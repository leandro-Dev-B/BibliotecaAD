package org.example.bibliotecaad.service;

import org.example.bibliotecaad.model.Usuario;
import org.example.bibliotecaad.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario criarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarUsuario(Usuario usuario) {
        if (usuario.getIdUsuario() == null || !usuarioRepository.existsById(usuario.getIdUsuario())) {
            throw new IllegalArgumentException("Usuário não encontrado para atualização");
        }
        return usuarioRepository.save(usuario);
    }

    public void deletarUsuario(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuário não encontrado para exclusão");
        }
        usuarioRepository.deleteById(id);
    }

}
