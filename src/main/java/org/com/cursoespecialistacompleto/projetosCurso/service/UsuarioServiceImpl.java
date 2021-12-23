package org.com.cursoespecialistacompleto.projetosCurso.service;

import org.com.cursoespecialistacompleto.projetosCurso.entities.Usuarios;
import org.com.cursoespecialistacompleto.projetosCurso.repository.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    public Usuarios cadastrarUsuario(Usuarios usuario){
        return usuarioDAO.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
       Usuarios usuario = usuarioDAO.findByLogin(login)
               .orElseThrow(() -> new UsernameNotFoundException("usuário não encontrado!"));
       String[] roles = usuario.isAdmin() ? new String[] {"ADIMIN", "USER"} : new String[]{"USER"};

       return User
               .builder()
               .username(usuario.getLogin())
               .password(usuario.getSenha())
               .roles(roles)
               .build();
    }
}
