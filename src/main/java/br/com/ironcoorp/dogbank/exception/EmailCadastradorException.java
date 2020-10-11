package br.com.ironcoorp.dogbank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailCadastradorException extends RuntimeException{
    public EmailCadastradorException(String email) {
        super("O Email " + email + " já está cadastrado");
    }
}
