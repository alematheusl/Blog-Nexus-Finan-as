package com.gamerblog.backend.repository;

import java.util.Optional;
import com.gamerblog.backend.user.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional <Usuario> findByEmail(String email);
}