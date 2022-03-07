package org.example.clientes.rest;

import lombok.RequiredArgsConstructor;
import org.example.clientes.model.entity.Cliente;
import org.example.clientes.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @GetMapping
    public List<Cliente> obeter(){
        return service.obeterTodos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save (@RequestBody @Valid Cliente cliente){
        return service.saveCliente(cliente);
    }

    @GetMapping("{id}")
    public Cliente achar(@PathVariable Integer id){
        return service.acharPorId(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        service.deletarId(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Cliente atualizarCliente){
        service.atualizarId(id, atualizarCliente);
    }
}
