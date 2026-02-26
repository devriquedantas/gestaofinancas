package com.henrique.smart_expense;

import com.henrique.smart_expense.model.Usuario;
import com.henrique.smart_expense.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SmartExpenseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartExpenseApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (repository.findByUsername("henrique").isEmpty()) {
                Usuario user = new Usuario();
                user.setUsername("henrique");

                user.setPassword(passwordEncoder.encode("123456"));
                repository.save(user);
                System.out.println(">>> Usuário 'henrique' criado com sucesso!");
            }
        };
    }
}