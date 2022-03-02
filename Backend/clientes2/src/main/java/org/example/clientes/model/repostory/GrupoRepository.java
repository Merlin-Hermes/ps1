package org.example.clientes.model.repostory;

import org.example.clientes.model.entity.Grupo;
import org.example.clientes.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {

    List<Grupo> findByUsuariosIn(List<Usuario> usuario);

}

