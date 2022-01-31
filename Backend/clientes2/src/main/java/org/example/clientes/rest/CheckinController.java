package org.example.clientes.rest;

import org.example.clientes.model.entity.Checkin;
import org.example.clientes.rest.dto.CheckinDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quartos")
public class CheckinController {

    @PostMapping
    public Checkin salvar(@RequestBody CheckinDTO dto){

    }
}
