package br.com.ironcoorp.dogbank.state;

import br.com.ironcoorp.dogbank.domain.StatusProposta;
import br.com.ironcoorp.dogbank.domain.Tutor;
import br.com.ironcoorp.dogbank.exception.StatusPropostaException;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;


public class PropostaIniciadaState implements PropostaState{

    @Enumerated(EnumType.STRING)
    private StatusProposta statusPropostaInter = StatusProposta.INICIADA;


    @Override
    public void iniciaProposta(Tutor tutor) {
        tutor.setStatusProposta(statusPropostaInter);
    }


    @Override
    public void complementoProposta(Tutor tutor) {
        tutor.setStatusProposta(statusPropostaInter);
    }

    @Override
    public void andamentoProposta(Tutor tutor) {
        throw new StatusPropostaException("A proposta ainda não foi Iniciada");
    }

    @Override
    public void aceiteProposta(Tutor tutor) {
        throw new StatusPropostaException("Ainda não é possível realizar finalizar a proposta neste momento. Ainda estamos conhecendo você ;)");
    }

    @Override
    public void finalizaProposta(Tutor tutor) {
        throw new StatusPropostaException("Ainda não é possível finalizar sua solicitação, falta só um pouquinho!!!");
    }

    @Override
    public void cancelaProposta(Tutor tutor) {
        throw new StatusPropostaException("Nesse momento não podemos atender sua solicitação sua  proposta está cancelada, em breve entraremos em contato com você, aceite ser um Tutor DOGBank :)!!!");
    }
}
