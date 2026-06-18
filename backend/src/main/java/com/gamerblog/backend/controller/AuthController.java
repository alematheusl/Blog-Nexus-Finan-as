package com.gamerblog.backend.controller;

import com.gamerblog.backend.user.Usuario;
import com.gamerblog.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody Usuario usuario) {
        if (usuario.getEmail() == null || usuario.getEmail().isBlank()) {
            return ResponseEntity.badRequest().body("E-mail e obrigatorio.");
        }
        if (usuario.getSenha() == null || usuario.getSenha().isBlank()) {
            return ResponseEntity.badRequest().body("Senha e obrigatoria.");
        }
        if (repository.findByEmail(usuario.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("E-mail ja cadastrado.");
        }
        repository.save(usuario);
        return ResponseEntity.status(201).body("Usuario cadastrado com sucesso!");
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody Usuario usuario) {
        if (usuario.getEmail() == null || usuario.getSenha() == null) {
            return ResponseEntity.badRequest().body(false);
        }
        boolean autorizado = repository.findByEmail(usuario.getEmail())
                .map(u -> u.getSenha().equals(usuario.getSenha()))
                .orElse(false);
        if (autorizado) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(401).body(false);
        }
    }
}