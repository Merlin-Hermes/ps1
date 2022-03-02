package org.example.clientes.model.repostory;

import org.example.clientes.model.entity.Grupo;
import org.example.clientes.model.entity.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

    List<Permissao> findByGruposIn(List<Grupo> grupo);

}