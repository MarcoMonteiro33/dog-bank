package br.com.ironcoorp.dogbank.handler;

import br.com.ironcoorp.dogbank.error.ErrorMessageDetails;
import br.com.ironcoorp.dogbank.error.ValidationErrorDetails;
import br.com.ironcoorp.dogbank.exception.CPFCadastradoException;
import br.com.ironcoorp.dogbank.exception.EmailCadastradorException;
import br.com.ironcoorp.dogbank.exception.IdadeNaoPermitidaException;
import br.com.ironcoorp.dogbank.exception.TutorNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

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

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handlerErrorExpection(IllegalArgumentException error){
        ErrorMessageDetails menssageError =   ErrorMessageDetails.ErrorMessageDetailsBuilder
                .newBuilder()
                .tempo(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .titulo(error.getMessage())
                .detalhe(error.getMessage())
                .messageDevelper(error.getClass().getName())
                .build();

        return new ResponseEntity<>(menssageError, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(TutorNotFoundException.class)
    public ResponseEntity<?> handlerErrorExpection(TutorNotFoundException error){
        ErrorMessageDetails menssageError =   ErrorMessageDetails.ErrorMessageDetailsBuilder
                .newBuilder()
                .tempo(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .titulo("Tutor não Localizado")
                .detalhe(error.getMessage())
                .messageDevelper(error.getClass().getName())
                .build();

        return new ResponseEntity<>(menssageError, HttpStatus.NOT_FOUND);
    }

    /**@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException argumentNotValidException,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<FieldError> fieldErrors = argumentNotValidException.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining("|"));
        String fieldMessages = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining("|"));
        ValidationErrorDetails errorDetails = ValidationErrorDetails.Builder
                .newBuilder()
                .tempo(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .titulo("Validação de Campos")
                .detalhe("Validação dos campos informados na requisão")
                .messageDevelper(argumentNotValidException.getClass().getName())
                .field(fields)
                .fieldMessage(fieldMessages)
                .build();
      return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }**/

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ValidationErrorDetails> errors = getErrors(ex);
        ErrorMessageDetails errorResponse = getErrorResponse(ex, status, errors);
        return new ResponseEntity<>(errorResponse, status);
    }


    private ErrorMessageDetails getErrorResponse(MethodArgumentNotValidException ex, HttpStatus httpStatus, List<ValidationErrorDetails> errors) {
        return ErrorMessageDetails.ErrorMessageDetailsBuilder
                .newBuilder()
                .titulo("A requisição possui campos inválidos")
                .status(httpStatus.value())
                .detalhe(ex.getBindingResult().getObjectName())
                .tempo(LocalDateTime.now())
                .messageDevelper("Falha na Validação dos Campos informados na requisição")
                .listErros(errors)
                .build();
    }

    private List<ValidationErrorDetails> getErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> ValidationErrorDetails.Builder.newBuilder()
                        .fieldMessage(error.getDefaultMessage())
                        .field(error.getField())
                        .detalhe(error.getDefaultMessage())
                        .build()
                    )
                .collect(Collectors.toList());
    }

}
