package com.henrique.smart_expense.repository;

import com.henrique.smart_expense.model.Despesas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DespesasRepository extends JpaRepository<Despesas, Long> {
    List<Despesas> findByUsuarioUsername(String username);
}