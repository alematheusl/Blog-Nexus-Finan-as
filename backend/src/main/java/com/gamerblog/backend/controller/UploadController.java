package com.gamerblog.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
public class UploadController {

    private static final String PASTA_UPLOADS = "uploads/";

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("arquivo") MultipartFile arquivo) {
        try {
            Path pasta = Paths.get(PASTA_UPLOADS);
            if (!Files.exists(pasta)) {
                Files.createDirectories(pasta);
            }

            String nomeArquivo = UUID.randomUUID() + "_" + arquivo.getOriginalFilename();
            Path destino = pasta.resolve(nomeArquivo);
            Files.copy(arquivo.getInputStream(), destino);

            String url = "http://localhost:8080/uploads/" + nomeArquivo;
            return ResponseEntity.ok(url);

        } catch (IOException e) {
            return ResponseEntity.status(500).body("Erro ao fazer upload.");
        }
    }

    @GetMapping("/uploads/{nomeArquivo}")
    public ResponseEntity<org.springframework.core.io.Resource> servirImagem(@PathVariable String nomeArquivo) {
        try {
            Path arquivo = Paths.get(PASTA_UPLOADS).resolve(nomeArquivo);
            org.springframework.core.io.Resource resource = new org.springframework.core.io.UrlResource(arquivo.toUri());

            if (resource.exists()) {
                return ResponseEntity.ok()
                        .header("Content-Type", "image/jpeg")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}