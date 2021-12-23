package org.com.cursoespecialistacompleto.projetosCurso.controllers;

import org.com.cursoespecialistacompleto.projetosCurso.entities.Cliente;
import org.com.cursoespecialistacompleto.projetosCurso.repository.ClienteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequestMapping("/cliente")
@RestController
public class ClienteController {

    @Autowired
    ClienteDAO clienteDAO;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente buscaPorId(@PathVariable Long id){
        return clienteDAO.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Id do cliente não encontrado na base de dados"));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> listarClientes(Cliente filtro){

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);//busco tudo que conter o filtro
        Example example = Example.of(filtro, matcher);
        return clienteDAO.findAll(example);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Cliente> adicionarCliente(@RequestBody Cliente cliente){

        Cliente clienteSalvo = clienteDAO.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente){

        clienteDAO.findById(id).map(clienteAtual -> {
            cliente.setId(clienteAtual.getId());
            clienteDAO.save(cliente);
            return cliente;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCliente(@PathVariable Long id){

        clienteDAO.findById(id)
                .map(c-> {
                    clienteDAO.delete(c);
                    return c;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }
}
