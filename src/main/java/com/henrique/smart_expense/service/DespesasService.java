package com.henrique.smart_expense.service;

import com.henrique.smart_expense.model.Despesas;
import com.henrique.smart_expense.model.Usuario;
import com.henrique.smart_expense.repository.DespesasRepository;
import com.henrique.smart_expense.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DespesasService {

    @Autowired
    private DespesasRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Usuario getUsuarioLogado() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + username));
    }

    public Despesas salvar(Despesas despesa) {

        despesa.setUsuario(getUsuarioLogado());
        return repository.save(despesa);
    }

    public List<Despesas> listarTodas() {

        Usuario logado = getUsuarioLogado();
        return repository.findByUsuarioUsername(logado.getUsername());
    }

    public void deletar(Long id) {

        repository.deleteById(id);
    }

    public Despesas atualizar(Long id, Despesas obj) {
        return repository.findById(id).map(entity -> {
            updateData(entity, obj);

            entity.setUsuario(getUsuarioLogado());
            return repository.save(entity);
        }).orElseThrow(() -> new RuntimeException("Despesa não encontrada com ID: " + id));
    }

    public void updateData(Despesas entity, Despesas obj) {
        entity.setDescricao(obj.getDescricao());
        entity.setValor(obj.getValor());
        entity.setCategoria(obj.getCategoria());
        entity.setData(obj.getData());
    }
}