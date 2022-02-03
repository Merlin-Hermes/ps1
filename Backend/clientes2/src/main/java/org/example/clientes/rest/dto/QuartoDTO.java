package org.example.clientes.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Data
@NoArgsConstructor
public class QuartoDTO {

    private String descricao;
    @NotEmpty(message = "{campo.quarto.obrigatorio}")
    private String Quarto;
    @NotEmpty(message = "{campo.data.obrigatorio}")
    private String data;
    private String valor;
    private Integer idCliente;
    @NotEmpty(message = "{campo.status.obrigatorio}")
    private String status;
}
