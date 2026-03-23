package org.example.bibliotecaad.service;

import org.example.bibliotecaad.entity.Usuario;
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

    public String buscarUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id)
                .map(Usuario::getNome)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
    }

    public Usuario buscarTudoDoUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
    }

    public void validarExistenciaUsuario(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuário com ID " + id + " não existe");
        }
    }
}