package com.henrique.smart_expense.service;

import com.henrique.smart_expense.model.Despesas;
import com.henrique.smart_expense.repository.DespesasRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DespesasService {


    @Autowired
    private DespesasRepository repository;

    public Despesas salvar(Despesas despesa){
        return repository.save(despesa);
    }

    public List<Despesas>listarTodas(){
        return repository.findAll();
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }
    public Despesas atualizar(Long id, Despesas obj) {

        return repository.findById(id).map(entity -> {
            updateData(entity, obj);
            return repository.save(entity);
        }).orElseThrow(() -> new RuntimeException("Despesa não encontrada com ID: " + id));
    }

    public void updateData(Despesas entity, Despesas obj){
        entity.setDescricao(obj.getDescricao());
        entity.setValor(obj.getValor());
        entity.setCategoria(obj.getCategoria());
        entity.setData(obj.getData());
    }
}
