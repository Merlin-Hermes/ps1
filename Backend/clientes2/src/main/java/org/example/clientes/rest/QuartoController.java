package org.example.clientes.rest;

import lombok.RequiredArgsConstructor;
import org.example.clientes.model.entity.Quarto;
import org.example.clientes.rest.dto.QuartoDTO;
import org.example.clientes.service.QuartoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/quartos")
@RequiredArgsConstructor
public class QuartoController {

    private final QuartoService quartoService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Quarto saveQuarto(@RequestBody @Valid Quarto quarto){
            return quartoService.save(quarto);
        }

    @GetMapping
    public List<Quarto> obterQuartos(){
        return quartoService.obter();
    }

    @GetMapping("/{id}")
    public Quarto acharQuartoPorId(@PathVariable Long id){
        return quartoService.achar(id);
    }

    @PutMapping("/{id}")
    public Quarto checkin(@PathVariable Long id, @RequestBody @Valid QuartoDTO dto){
        return quartoService.checkin(id, dto);
    }
}
