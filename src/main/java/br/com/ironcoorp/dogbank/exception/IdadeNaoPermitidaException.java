package br.com.ironcoorp.dogbank.exception;

public class IdadeNaoPermitidaException extends RuntimeException {
    public IdadeNaoPermitidaException(Long idade) {
        super("Não é permitida a Solicitação de Proposta para Idade de "+idade+ " anos!");
    }
}
