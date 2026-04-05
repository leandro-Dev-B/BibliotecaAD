package org.example.bibliotecaad.repository;

import org.example.bibliotecaad.entity.Emprestimo;
import org.example.bibliotecaad.entity.enums.StatusEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer> {

    List<Emprestimo> findByStatusEmprestimo(StatusEmprestimo statusEmprestimo);
}
