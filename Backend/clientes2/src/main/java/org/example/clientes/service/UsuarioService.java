package org.example.clientes.service;

import org.example.clientes.model.entity.Usuario;
import org.example.clientes.model.repostory.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Service
public class UsuarioService {

    UsuarioRepository repository;

    public void salvar(@RequestBody @Valid Usuario usuario){
        repository.save(usuario);
    }

}
