package org.example.bibliotecaad.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.example.bibliotecaad.dto.EmprestimoDto;
import org.example.bibliotecaad.entity.Emprestimo;
import org.example.bibliotecaad.service.EmprestimoService;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("{idUsuario}/{idLivro}")
    @Operation(summary = "Cria uma operação de empréstimo de livro para um usuário.")
    @ApiResponse(responseCode = "201", description = "Empréstimo criado com sucesso.")
    public ResponseEntity<EmprestimoDto> criarEmprestimo(@PathVariable ("idUsuario") Integer idUsuario, @PathVariable ("idLivro") Integer idLivro) {
        return ResponseEntity.ok(emprestimoService.criarEmprestimo(idUsuario, idLivro));
    }

    @DeleteMapping
    public void deletarEmprestimo(Integer id) {
        emprestimoService.deletarEmprestimo(id);
    }

}
