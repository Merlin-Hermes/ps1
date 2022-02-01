package org.example.clientes.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CheckinDTO {
    private String descricao;
    private String quarto;
    private String data;
    private String valor;
    private Integer idCliente;
}
