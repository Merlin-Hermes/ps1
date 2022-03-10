package org.example.clientes.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class QuartoDTO {

    @NotEmpty(message = "{campo.descricao.obrigatorio}")
    private String descricao;
    private Long id;
    private String data;
    private String valor;
    private Long idCliente;
    @NotEmpty(message = "{campo.status.obrigatorio}")
    private String status;

}