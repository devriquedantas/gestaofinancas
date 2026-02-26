package com.henrique.smart_expense.repository;

import com.henrique.smart_expense.model.Despesas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DespesasRepository extends JpaRepository<Despesas, Long> {
    // Pronto! Você já tem métodos como save() e findAll() sem escrever nada.
}