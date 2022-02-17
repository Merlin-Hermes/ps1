package org.example.clientes.service;

import org.example.clientes.model.entity.Usuario;
import org.example.clientes.model.repostory.UsuarioRepository;
import org.example.clientes.rest.exception.UsuarioCadastradoExecption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    UsuarioRepository repository;

    public Usuario salvar(Usuario usuario){
        boolean exists = repository.existsByUsername(usuario.getUsername());
        if (exists){
            throw new UsuarioCadastradoExecption(usuario.getUsername());
        }
        return repository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("login não encontrado. "));
        return User.builder().username(usuario.getUsername()).password(usuario.getPassword()).roles("USER").build();
    }
}
