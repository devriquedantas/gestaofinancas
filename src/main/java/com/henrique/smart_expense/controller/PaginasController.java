package com.henrique.smart_expense.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginasController {


    @GetMapping("/login")
    public String exibirLogin() {
        return "login";
    }


    @GetMapping("/")
    public String exibirIndex() {
        return "index";
    }
    @GetMapping("/cadastro")
    public String exibirCadastro() {
        return "cadastro";
    }
}