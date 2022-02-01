package org.example.clientes.rest;

import lombok.RequiredArgsConstructor;
import org.example.clientes.model.entity.Checkin;
import org.example.clientes.model.entity.Cliente;
import org.example.clientes.model.repostory.CheckinRepository;
import org.example.clientes.model.repostory.ClienteRepository;
import org.example.clientes.rest.dto.CheckinDTO;
import org.example.clientes.ultil.BigDecimalConverter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RestController
@RequestMapping("/api/quartos")
@RequiredArgsConstructor
public class CheckinController {

    private final ClienteRepository clienteRepository;
    private final CheckinRepository checkinRepository;
    private final BigDecimalConverter bigDecimalConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Checkin salvar(@RequestBody CheckinDTO dto){
       LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
       Integer idCliente = dto.getIdCliente();

       Cliente cliente =
               clienteRepository
                       .findById(idCliente)
                       .orElseThrow(() ->
                               new ResponseStatusException(
                                       HttpStatus.BAD_REQUEST, "cliente inexistente"));

        Checkin checkin = new Checkin();
        checkin.setDescricao(dto.getDescricao());
        checkin.setData(data);
        checkin.setCliente(cliente);
        checkin.setQuarto(dto.getQuarto());
        checkin.setValor(bigDecimalConverter.converter(dto.getValor()));

        return checkinRepository.save(checkin);

    }
    @GetMapping
    public List<Checkin> pesquisar(
            @RequestParam(value = "nome", required = false, defaultValue = "") String nome,
            @RequestParam(value = "mes", required = false) Integer mes
    ){
        return clienteRepository.findByNomeClienteAndMes("%" + nome + "%", mes);
    }
}
