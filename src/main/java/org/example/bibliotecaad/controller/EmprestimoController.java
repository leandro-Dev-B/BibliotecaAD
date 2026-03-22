package org.example.bibliotecaad.controller;

import org.example.bibliotecaad.model.Emprestimo;
import org.example.bibliotecaad.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @GetMapping
    public List<Emprestimo> listarTodos() {
        return emprestimoService.listarTodos();
    }

    @PostMapping
    public Emprestimo criarEmprestimo(Emprestimo emprestimo) {
        return emprestimoService.criarEmprestimo(emprestimo);
    }

    @PutMapping
    public Emprestimo atualizarEmprestimo(Emprestimo emprestimo) {
        return emprestimoService.atualizarEmprestimo(emprestimo);
    }

    @DeleteMapping
    public void deletarEmprestimo(Integer id) {
        emprestimoService.deletarEmprestimo(id);
    }

}
