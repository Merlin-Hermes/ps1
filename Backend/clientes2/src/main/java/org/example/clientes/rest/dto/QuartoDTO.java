package org.example.clientes.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class QuartoDTO {

    private String descricao;
    @NotEmpty(message = "{campo.quarto.obrigatorio}")
    private String Quarto;
    @NotEmpty(message = "{campo.data.obrigatorio}")
    private String data;
    @NotEmpty(message = "{campo.quarto.obrigatorio}")
    private String valor;
    @NotNull(message = "{campo.idCliente.obrigatorio}")
    private Integer idCliente;
}
