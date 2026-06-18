package com.gamerblog.backend.repository;

import com.gamerblog.backend.model.Artigo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtigoRepository extends JpaRepository<Artigo, Long> {

    // Todos os artigos publicados
    List<Artigo> findByPublicadoTrue();

    // Busca por título apenas entre publicados
    List<Artigo> findByTituloContainingIgnoreCaseAndPublicadoTrue(String titulo);
}
