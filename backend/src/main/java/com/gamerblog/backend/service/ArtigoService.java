package com.gamerblog.backend.service;

import com.gamerblog.backend.model.Artigo;
import com.gamerblog.backend.repository.ArtigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ArtigoService {

    @Autowired
    private ArtigoRepository repository;

    // Apenas publicados — para o blog público
    public List<Artigo> listarPublicados() {
        return repository.findByPublicadoTrue();
    }

    // Todos — para o painel admin
    public List<Artigo> listarTodos() {
        return repository.findAll();
    }

    public Optional<Artigo> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // Busca por título (só publicados)
    public List<Artigo> buscarPorTitulo(String titulo) {
        return repository.findByTituloContainingIgnoreCaseAndPublicadoTrue(titulo);
    }

    public Artigo criar(Artigo artigo) {
        if (artigo.isPublicado()) {
            artigo.setPublicadoEm(LocalDateTime.now());
        }
        return repository.save(artigo);
    }

    public Artigo atualizar(Long id, Artigo dados) {
        Artigo artigo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artigo nao encontrado"));

        artigo.setTitulo(dados.getTitulo());
        artigo.setConteudo(dados.getConteudo());
        artigo.setResumo(dados.getResumo());
        artigo.setAutor(dados.getAutor());
        artigo.setTags(dados.getTags());
        artigo.setImagemUrl(dados.getImagemUrl());
        
        if (dados.isPublicado() && !artigo.isPublicado()) {
            artigo.setPublicado(true);
            artigo.setPublicadoEm(LocalDateTime.now());
        } else if (!dados.isPublicado()) {
            artigo.setPublicado(false);
            artigo.setPublicadoEm(null);
        }

        return repository.save(artigo);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public Artigo alterarPublicacao(Long id, boolean publicado) {
        Artigo artigo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artigo nao encontrado"));
        artigo.setPublicado(publicado);
        artigo.setPublicadoEm(publicado ? LocalDateTime.now() : null);
        return repository.save(artigo);
    }
}
