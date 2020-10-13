package br.com.ironcoorp.dogbank.state;

import br.com.ironcoorp.dogbank.domain.StatusProposta;
import br.com.ironcoorp.dogbank.domain.Tutor;
import br.com.ironcoorp.dogbank.exception.StatusPropostaException;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class PropostaAndamentoState implements PropostaState{

    @Enumerated(EnumType.STRING)
    private StatusProposta statusPropostaInter = StatusProposta.ANDAMENTO;


    @Override
    public void iniciaProposta(Tutor tutor) {
        throw new StatusPropostaException("A proposta já foi Iniciada");
    }

    @Override
    public void complementoProposta(Tutor tutor) {
        throw new StatusPropostaException("Os dados complementares já foram enviados");

    }

    @Override
    public void andamentoProposta(Tutor tutor) {
        tutor.setStatusProposta(statusPropostaInter);
    }

    @Override
    public void aceiteProposta(Tutor tutor) {
        throw new StatusPropostaException("Ainda não é possível realizar finalizar a proposta neste momento, pois a Solicitação ainda está em Andamento");

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
