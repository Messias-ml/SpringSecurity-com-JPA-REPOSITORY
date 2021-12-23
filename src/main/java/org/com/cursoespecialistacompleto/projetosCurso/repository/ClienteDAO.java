package org.com.cursoespecialistacompleto.projetosCurso.repository;

import org.com.cursoespecialistacompleto.projetosCurso.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteDAO extends JpaRepository<Cliente, Long> {
}
