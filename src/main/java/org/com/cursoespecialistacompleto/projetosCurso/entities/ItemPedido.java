package org.com.cursoespecialistacompleto.projetosCurso.entities;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
    int quantidade;

}
