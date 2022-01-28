package org.example.clientes.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 250)
    private String descrição;

    @Column(nullable = false, length = 3)
    private Integer numeroDoQuarto;

    @ManyToOne
    @JoinColumn
    private Cliente cliente;

    @Column(nullable = false, length = 5)
    private BigDecimal valor;




}
