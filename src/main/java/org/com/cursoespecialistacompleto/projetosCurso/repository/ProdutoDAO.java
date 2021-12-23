package org.com.cursoespecialistacompleto.projetosCurso.repository;

import org.com.cursoespecialistacompleto.projetosCurso.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoDAO extends JpaRepository<Produto, Long> {
}
