package org.com.cursoespecialistacompleto.projetosCurso.controllers;

import lombok.RequiredArgsConstructor;
import org.com.cursoespecialistacompleto.projetosCurso.entities.Usuarios;
import org.com.cursoespecialistacompleto.projetosCurso.service.UsuarioServiceImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/usuario")
@RequiredArgsConstructor
public class UsuarioController {


    private final PasswordEncoder encoder;
    private final UsuarioServiceImpl usuarioService;

    @PostMapping
    public Usuarios criarUsuarios(@RequestBody Usuarios usuario){
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return usuarioService.cadastrarUsuario(usuario);
    }
}
