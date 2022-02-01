package org.example.clientes.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Checkin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 250)
    private String descricao;

    @Column(nullable = false, length = 1)
    private String quarto;

    @ManyToOne
    @JoinColumn

    private Cliente cliente;

    @Column(nullable = false, length = 10)
    private BigDecimal valor;

    @Column(name = "data_checkin", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;



}