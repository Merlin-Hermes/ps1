package org.example.clientes.service;

import org.example.clientes.model.entity.Usuario;
import org.example.clientes.model.repostory.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    UsuarioRepository repository;

    public Usuario salvar(Usuario usuario){
        return repository.save(usuario);
    }

}
