package br.com.ironcoorp.dogbank.exception;

public class TutorNotFoundException extends RuntimeException{
    public TutorNotFoundException(Long id) {
        super("Nenhum Tutor Localizado com o Id: "+id);
    }
}
