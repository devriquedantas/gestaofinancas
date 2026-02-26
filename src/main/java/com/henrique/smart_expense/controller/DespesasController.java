package com.henrique.smart_expense.controller;

import com.henrique.smart_expense.model.Despesas;
import com.henrique.smart_expense.service.DespesasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/despesas")
@CrossOrigin("*")
public class DespesasController {

    @Autowired
    private DespesasService service;

    @PostMapping
    public Despesas criar(@RequestBody Despesas despesa){
        return service.salvar(despesa);
    }

    @GetMapping
    public List<Despesas> listarTodas(){
        return service.listarTodas();
    }

    @DeleteMapping(value = "/{id}")
    public void deletar(@PathVariable Long id){
        service.deletar(id);
    }

    @PutMapping(value = "/{id}")
    public Despesas atualizar(@PathVariable Long id, @RequestBody Despesas obj) {
        return service.atualizar(id, obj);
    }
}
