package com.gamerblog.backend.controller;

import com.gamerblog.backend.model.Artigo;
import com.gamerblog.backend.service.ArtigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ArtigoController {

    @Autowired
    private ArtigoService artigoService;

    // -------------------------------------------------------
    // ROTAS PÚBLICAS — só artigos publicados
    // -------------------------------------------------------

    // Lista todos os artigos publicados (página inicial do blog)
    @GetMapping("/artigos")
    public List<Artigo> listarPublicados(@RequestParam(required = false) String titulo) {
        if (titulo != null && !titulo.isBlank()) {
            return artigoService.buscarPorTitulo(titulo);
        }
        return artigoService.listarPublicados();
    }

    // Busca um artigo por ID
    @GetMapping("/artigos/{id}")
    public ResponseEntity<Artigo> buscarPorId(@PathVariable Long id) {
        return artigoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // -------------------------------------------------------
    // ROTAS ADMINISTRATIVAS — todos os artigos
    // -------------------------------------------------------

    // Lista todos os artigos (publicados e rascunhos) para o painel admin
    @GetMapping("/admin/artigos")
    public List<Artigo> listarTodos() {
        return artigoService.listarTodos();
    }

    // Cria novo artigo
    @PostMapping("/artigos")
    public ResponseEntity<Artigo> criar(@RequestBody Artigo artigo) {
        Artigo criado = artigoService.criar(artigo);
        return ResponseEntity.status(201).body(criado);
    }

    // Atualiza artigo existente
    @PutMapping("/artigos/{id}")
    public ResponseEntity<Artigo> atualizar(@PathVariable Long id, @RequestBody Artigo artigo) {
        try {
            artigo.setId(id);
            Artigo atualizado = artigoService.atualizar(id, artigo);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Exclui artigo
    @DeleteMapping("/artigos/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            artigoService.excluir(id);
            return ResponseEntity.noContent().build(); // 204
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
