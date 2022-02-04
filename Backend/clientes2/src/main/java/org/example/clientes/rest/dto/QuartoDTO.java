package org.example.clientes.rest.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.clientes.model.entity.Cliente;

import javax.validation.constraints.NotEmpty;


@Data
@NoArgsConstructor
@Getter
public class QuartoDTO {

    @NotEmpty(message = "{campo.descricao.obrigatorio}")
    private String descricao;
    private Integer id;
    private String data;
    private String valor;
    private Integer idCliente;
    @NotEmpty(message = "{campo.status.obrigatorio}")
    private String status;

    public Cliente getIdCliente(Cliente cliente) {
        return null;
    }
}
