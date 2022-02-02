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
@CrossOrigin("*")
public class QuartoController {

    private final ClienteRepository clienteRepository;
    private final QuartoRepository quartoRepository;
    private final BigDecimalConverter bigDecimalConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Quarto saveQuarto (@RequestBody @Valid Quarto quarto){
        return quartoRepository.save(quarto);
    }

    @GetMapping
    public List<Quarto> pesquisar(
            @RequestParam(value = "nome", required = false, defaultValue = "") String nome,
            @RequestParam(value = "mes", required = false) Integer mes
    ){
        return clienteRepository.findByNomeClienteAndMes("%" + nome + "%", mes);
    }

    @PutMapping
    public Quarto checkin(@RequestBody @Valid QuartoDTO dto){
        Integer idCliente = dto.getIdCliente();
        LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Cliente cliente =
                clienteRepository
                        .findById(idCliente)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.BAD_REQUEST, "cliente inexistente"));


        Quarto quarto = new Quarto();
        quarto.setDescricao(dto.getDescricao());
        quarto.setData(data);
        quarto.setCliente(cliente);
        quarto.setValor(bigDecimalConverter.converter(dto.getValor()));

        return quartoRepository.save(quarto);

    }
}
