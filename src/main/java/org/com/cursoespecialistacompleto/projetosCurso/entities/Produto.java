package org.com.cursoespecialistacompleto.projetosCurso.entities;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Table(name = "produto")
@Entity
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    
    private String descricao;

    @Column(scale = 2)
    double preco;

}