package org.example.clientes.service;

import lombok.RequiredArgsConstructor;
import org.example.clientes.model.entity.Cliente;
import org.example.clientes.model.entity.Quarto;
import org.example.clientes.model.repostory.ClienteRepository;
import org.example.clientes.model.repostory.QuartoRepository;
import org.example.clientes.rest.dto.QuartoDTO;
import org.example.clientes.ultil.BigDecimalConverter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuartoService {

    private final ClienteRepository clienteRepository;
    private final QuartoRepository quartoRepository;
    private final BigDecimalConverter bigDecimalConverter;

    public Quarto save(@RequestBody @Valid Quarto quarto){
        return quartoRepository.save(quarto);
    }

    public List<Quarto> obter(){
        return quartoRepository.findAll();
    }

    public Quarto achar(@PathVariable Long id){
        return quartoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Quarto não encontrado"));
    }

    public Quarto checkin(@PathVariable Long id, @RequestBody @Valid QuartoDTO dto){
        Quarto quart = new Quarto();
        Long idCliente = dto.getIdCliente();
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
