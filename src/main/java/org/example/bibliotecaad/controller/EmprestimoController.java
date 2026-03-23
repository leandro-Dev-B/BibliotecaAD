package org.example.bibliotecaad.controller;

import org.example.bibliotecaad.model.Emprestimo;
import org.example.bibliotecaad.service.EmprestimoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

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
