package org.example.clientes.service;

import lombok.RequiredArgsConstructor;
import org.example.clientes.model.entity.Cliente;
import org.example.clientes.model.repostory.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    public List<Cliente> obeterTodos(){
        return repository.findAll();
    }

    public Cliente saveCliente (@RequestBody @Valid Cliente cliente){
        return repository.save(cliente);
    }

    public Cliente acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    public void deletarId(@PathVariable Integer id){
        repository.findById(id)
                .map(cliente -> {
                    repository.delete(cliente);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado"));
    }

    public void atualizarId(@PathVariable Integer id, @RequestBody @Valid Cliente atualizarCliente){
        repository.findById(id)
                .map(cliente -> {
                    atualizarCliente.setId(cliente.getId());
                    return repository.save(atualizarCliente);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado"));
    }

}
