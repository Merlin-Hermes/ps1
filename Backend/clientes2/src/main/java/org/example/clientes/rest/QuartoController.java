package org.example.clientes.rest;

import lombok.RequiredArgsConstructor;
import org.example.clientes.model.entity.Cliente;
import org.example.clientes.model.entity.Quarto;
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
import java.util.Optional;


@RestController
@RequestMapping("/api/quartos")
@RequiredArgsConstructor
public class QuartoController {

    private final ClienteRepository clienteRepository;
    private final QuartoRepository quartoRepository;
    private final BigDecimalConverter bigDecimalConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Quarto saveQuarto(@RequestBody @Valid Quarto quarto){
            return quartoRepository.save(quarto);
        }

    @GetMapping
    public List<Quarto> obterQuartos(){
        return quartoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Quarto acharQuartoPorId(@PathVariable Integer id){
        return quartoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Quarto não encontrado"));
    }

    @PutMapping("/{id}")
    public Quarto checkin(@PathVariable Integer id, @RequestBody @Valid QuartoDTO dto){
        Quarto quart = new Quarto();
        Integer idCliente = dto.getIdCliente();
        Optional<Cliente> clienteOptional = clienteRepository.findById(idCliente);
        Cliente cliente = clienteOptional.orElse(new Cliente());

        quartoRepository.findById(id)
                .map(quarto -> {
                    LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    quart.setDescricao(dto.getDescricao());
                    quart.setStatus(dto.getStatus());
                    quart.setId(quarto.getId());
                    quart.setData(data);
                    quart.setCliente(cliente);
                    quart.setValor(bigDecimalConverter.converter(dto.getValor()));
                     return quartoRepository.save(quart);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "quarto não encontrado"));
                        return quart;
    }
}
