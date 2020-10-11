package br.com.ironcoorp.dogbank.handler;

import br.com.ironcoorp.dogbank.error.ErrorMessageDetails;
import br.com.ironcoorp.dogbank.exception.CPFCadastradoException;
import br.com.ironcoorp.dogbank.exception.EmailCadastradorException;
import br.com.ironcoorp.dogbank.exception.IdadeNaoPermitidaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestExceptionHandler{

    @ExceptionHandler(EmailCadastradorException.class)
    public ResponseEntity<?> handlerErrorExpection(EmailCadastradorException error){
        ErrorMessageDetails menssageError =   ErrorMessageDetails.ErrorMessageDetailsBuilder
                .newBuilder()
                .tempo(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .titulo("Validação do Email")
                .detalhe(error.getMessage())
                .messageDevelper(error.getClass().getName())
                .build();

        return new ResponseEntity<>(menssageError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CPFCadastradoException.class)
    public ResponseEntity<?> handlerErrorExpection(CPFCadastradoException error){
        ErrorMessageDetails menssageError =   ErrorMessageDetails.ErrorMessageDetailsBuilder
                .newBuilder()
                .tempo(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .titulo("Validação do CPF")
                .detalhe(error.getMessage())
                .messageDevelper(error.getClass().getName())
                .build();

        return new ResponseEntity<>(menssageError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IdadeNaoPermitidaException.class)
    public ResponseEntity<?> handlerErrorExpection(IdadeNaoPermitidaException error){
        ErrorMessageDetails menssageError =   ErrorMessageDetails.ErrorMessageDetailsBuilder
                .newBuilder()
                .tempo(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .titulo("Validação da Idade")
                .detalhe(error.getMessage())
                .messageDevelper(error.getClass().getName())
                .build();

        return new ResponseEntity<>(menssageError, HttpStatus.BAD_REQUEST);
    }
}
