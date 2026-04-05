package org.example.bibliotecaad.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.example.bibliotecaad.dto.EmprestimoDto;
import org.example.bibliotecaad.entity.Emprestimo;
import org.example.bibliotecaad.service.EmprestimoService;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
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

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> buscarEmprestimoPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(emprestimoService.buscarEmprestimoPorId(id).orElseThrow(() -> new IllegalArgumentException("Empréstimo com ID " + id + " não encontrado")));
    }

    @PostMapping("{idUsuario}/{idLivro}")
    @Operation(summary = "Cria uma operação de empréstimo de livro para um usuário.")
    @ApiResponse(responseCode = "201", description = "Empréstimo criado com sucesso.")
    public ResponseEntity<EmprestimoDto> criarEmprestimo(@PathVariable ("idUsuario") Integer idUsuario, @PathVariable ("idLivro") Integer idLivro) {
        return ResponseEntity.ok(emprestimoService.criarEmprestimo(idUsuario, idLivro));
    }

    @PostMapping("{idEmprestimo}/devolucao")
    @Operation (summary = "Registra a devolução de um livro emprestado.")
    @ApiResponse(responseCode = "200", description = "Devolução registrada com sucesso.")
    public ResponseEntity<EmprestimoDto> registrarDevolucao(@PathVariable Integer idEmprestimo) {
        return ResponseEntity.ok(emprestimoService.devolucaoDeEmprestimo(idEmprestimo));
    }

    @DeleteMapping
    public void deletarEmprestimo(Integer id) {
        emprestimoService.deletarEmprestimo(id);
    }

    @Scheduled(cron = "0 0 0 * * ?") // Executa diariamente à meia-noite
    public void verificarEmprestimosAtrasados() {
        emprestimoService.verificarEmprestimosAtrasados();
    }

    @PostMapping("/verificar-atrasados")
    @Operation (summary = "Verifica manualmente os empréstimos atrasados e atualiza seus status.")
    @ApiResponse(responseCode = "200", description = "Status dos empréstimos atualizado com base na data de devolução prevista.")
    public String verificarEmprestimosAtrasadosManual(){
        emprestimoService.verificarEmprestimosAtrasados();
        return "Verificação manual de empréstimos atrasados concluída. Status atualizado conforme necessário.";
    }

}
