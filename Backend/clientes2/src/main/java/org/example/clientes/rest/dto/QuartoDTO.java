package org.example.clientes.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Data
@NoArgsConstructor
public class QuartoDTO {

    @NotEmpty(message = "{campo.descricao.obrigatorio}")
    private String descricao;
    private Integer id;
    private String data;
    private String valor;
    private Integer idCliente;
    @NotEmpty(message = "{campo.status.obrigatorio}")
    private String status;
}
