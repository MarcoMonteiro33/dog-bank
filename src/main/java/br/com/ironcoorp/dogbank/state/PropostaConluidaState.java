package br.com.ironcoorp.dogbank.state;

import br.com.ironcoorp.dogbank.domain.StatusProposta;
import br.com.ironcoorp.dogbank.domain.Tutor;
import br.com.ironcoorp.dogbank.exception.StatusPropostaException;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class PropostaConluidaState implements PropostaState{

    @Enumerated(EnumType.STRING)
    private StatusProposta statusPropostaInter = StatusProposta.CONCLUIDO;

    @Override
    public void iniciaProposta(Tutor tutor) {
        throw new StatusPropostaException("A proposta já está iniciada");
    }

    @Override
    public void complementoProposta(Tutor tutor) {
        throw new StatusPropostaException("Os Dados complementares já foram enviados");
    }

    @Override
    public void andamentoProposta(Tutor tutor) {
        throw new StatusPropostaException("Os anexos já foram enviados");
    }

    @Override
    public void aceiteProposta(Tutor tutor) {
        throw new StatusPropostaException("Você já aceitou a abertura de conta digital no DOGbank :)!");
    }

    @Override
    public void finalizaProposta(Tutor tutor) {
        tutor.setStatusProposta(statusPropostaInter);
    }

    @Override
    public void cancelaProposta(Tutor tutor) {
        throw new StatusPropostaException("Nesse momento não podemos atender sua solicitação sua  proposta está cancelada, em breve entraremos em contato com você, aceite ser um Tutor DOGBank :)!!!");
    }
}
