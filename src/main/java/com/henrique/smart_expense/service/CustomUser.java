package com.henrique.smart_expense.service;

import com.henrique.smart_expense.model.Usuario;
import com.henrique.smart_expense.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUser implements UserDetailsService {
    private final UsuarioRepository repository;
    public CustomUser(UsuarioRepository repository){
        this.repository = repository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    return User.builder()
            .username(usuario.getUsername())
            .password(usuario.getPassword())
            .roles("USER")
            .build();
    }
}
