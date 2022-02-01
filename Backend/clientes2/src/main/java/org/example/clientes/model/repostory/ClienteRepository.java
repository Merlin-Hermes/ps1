package org.example.clientes.model.repostory;

import org.example.clientes.model.entity.Checkin;
import org.example.clientes.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query(" select s from Checkin s join s.cliente c where upper(c.nome) like upper(:nome) and MONTH(s.data) =:mes ")
    List<Checkin> findByNomeClienteAndMes(
            @Param("nome") String nome, @Param("mes") Integer mes);
}
