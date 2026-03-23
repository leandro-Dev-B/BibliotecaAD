package org.example.bibliotecaad.controller;


import org.example.bibliotecaad.entity.Usuario;
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

    @GetMapping("/{id}")
    public Usuario buscarUsuarioPorId(@PathVariable Integer id) {
        return usuarioService.buscarTudoDoUsuarioPorId(id);
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
