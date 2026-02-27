package com.henrique.smart_expense.controller;

import com.henrique.smart_expense.model.Usuario;
import com.henrique.smart_expense.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/usuario/cadastrar")
    public String cadastrar(Usuario usuario) {

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        repository.save(usuario);
        return "redirect:/login?success";
    }
}
