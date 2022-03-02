package org.example.clientes.service;

import org.example.clientes.config.UsuarioSistema;
import org.example.clientes.model.entity.Grupo;
import org.example.clientes.model.entity.Permissao;
import org.example.clientes.model.entity.Usuario;
import org.example.clientes.model.repostory.GrupoRepository;
import org.example.clientes.model.repostory.PermissaoRepository;
import org.example.clientes.model.repostory.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Component
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarios;

    @Autowired
    private GrupoRepository grupos;

    @Autowired
    private PermissaoRepository permissoes;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarios.findByLogin(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }

        return new UsuarioSistema(usuario.getNome(), usuario.getLogin(), usuario.getSenha(), authorities(usuario));
    }

    public Collection<? extends GrantedAuthority> authorities(Usuario usuario) {
        return authorities(grupos.findByUsuariosIn(newArrayList(usuario)));
    }

    public Collection<? extends GrantedAuthority> authorities(List<Grupo> grupos) {
        Collection<GrantedAuthority> auths = new ArrayList<>();

        for (Grupo grupo: grupos) {
            List<Permissao> lista = permissoes.findByGruposIn(newArrayList(grupo));

            for (Permissao permissao: lista) {
                auths.add(new SimpleGrantedAuthority("ROLE_" + permissao.getNome()));
            }
        }

        return auths;
    }

}