package org.example.clientes.rest.exception;

public class UsuarioCadastradoExecption extends RuntimeException{

    public UsuarioCadastradoExecption(String login){
        super("Usuario ja cadastrado " + login);
    }

}
