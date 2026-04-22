package com.example.demo.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // exemplo útil para autenticação: encontrar por email
    Optional<Usuario> findByEmail(String email);
}