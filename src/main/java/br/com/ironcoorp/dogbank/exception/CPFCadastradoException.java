package br.com.ironcoorp.dogbank.exception;

public class CPFCadastradoException extends RuntimeException{
    public CPFCadastradoException(String cpf) {
        super("O CPF: "+cpf+ " já está cadastrado");
    }
}
