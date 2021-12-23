package org.com.cursoespecialistacompleto.projetosCurso.repository;

import org.com.cursoespecialistacompleto.projetosCurso.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoDAO extends JpaRepository<Pedido, Long> {
}
