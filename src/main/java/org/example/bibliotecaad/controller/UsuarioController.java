package org.example.bibliotecaad.controller;


import org.example.bibliotecaad.model.Usuario;
import org.example.bibliotecaad.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioService.listarTodos();
    }

    @PostMapping
    public Usuario criarUsuario(Usuario usuario) {
        return usuarioService.criarUsuario(usuario);
    }

    @PutMapping
    public Usuario atualizarUsuario(Usuario usuario) {
        return usuarioService.atualizarUsuario(usuario);
    }

    @DeleteMapping
    public void deletarUsuario(Integer id) {
        usuarioService.deletarUsuario(id);
    }

}
