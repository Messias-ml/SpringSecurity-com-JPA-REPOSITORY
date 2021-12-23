package org.com.cursoespecialistacompleto.projetosCurso.controllers;

import org.com.cursoespecialistacompleto.projetosCurso.entities.Cliente;
import org.com.cursoespecialistacompleto.projetosCurso.entities.Produto;
import org.com.cursoespecialistacompleto.projetosCurso.repository.ProdutoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequestMapping("/produto")
@RestController
public class ProdutoController {

    @Autowired
    ProdutoDAO produtoDAO;

    @GetMapping("/{id}")
    public Produto buscaPorId(@PathVariable Long id){
        return produtoDAO.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Id do cliente não encontrado na base de dados"));
    }

    @GetMapping
    public List<Produto> listarClientes(Cliente filtro){

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);//busco tudo que conter o filtro
        Example example = Example.of(filtro, matcher);
        return produtoDAO.findAll(example);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Produto> adicionarCliente(@RequestBody Produto produto){

        produtoDAO.save(produto);
        return ResponseEntity.ok(produto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarCliente(@PathVariable Long id, @RequestBody Produto produto){

        produtoDAO.findById(id).map(produtoAtual -> {
            produto.setId(produtoAtual.getId());
            produtoDAO.save(produto);
            return produto;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCliente(@PathVariable Long id){

        produtoDAO.findById(id)
                .map(p-> {
                    produtoDAO.delete(p);
                    return p;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

}
