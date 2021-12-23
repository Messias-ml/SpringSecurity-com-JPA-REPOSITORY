package org.com.cursoespecialistacompleto.projetosCurso.repository;

import org.com.cursoespecialistacompleto.projetosCurso.entities.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuarios, Long> {

    Optional<Usuarios> findByLogin(String login);
}
