package org.example.clientes.rest;

import lombok.RequiredArgsConstructor;
import org.example.clientes.model.entity.Quarto;
import org.example.clientes.model.entity.Cliente;
import org.example.clientes.model.repostory.QuartoRepository;
import org.example.clientes.model.repostory.ClienteRepository;
import org.example.clientes.rest.dto.QuartoDTO;
import org.example.clientes.ultil.BigDecimalConverter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RestController
@RequestMapping("/api/quartos")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class QuartoController {

    private final ClienteRepository clienteRepository;
    private final QuartoRepository quartoRepository;
    private final BigDecimalConverter bigDecimalConverter;
    Quarto quarto = new Quarto();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Quarto saveQUarto(@RequestBody @Valid QuartoDTO dto){

            quarto.setDescricao(dto.getDescricao());
            quarto.setStatus(dto.getStatus());

            return quartoRepository.save(quarto);

        }



    @GetMapping
    public List<Quarto> obterQuartos(){
        return quartoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Quarto acharQuartoPorId(@PathVariable Integer id){
        return quartoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @PutMapping("/{id}")
    public Quarto checkin( @RequestBody @PathVariable Integer id, QuartoDTO dto){

        Integer idCliente = dto.getIdCliente();
        LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() ->
                new ResponseStatusException(HttpStatus
                .BAD_REQUEST, "cliente inexistente"));

        quarto.setCliente(dto.getIdCliente(cliente));
        quarto.setData(data);
        quarto.setValor(bigDecimalConverter.converter(dto.getValor()));
        return quartoRepository.save(quarto);

    }

}
