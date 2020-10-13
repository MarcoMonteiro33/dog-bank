package br.com.ironcoorp.dogbank.state;

import br.com.ironcoorp.dogbank.domain.StatusProposta;
import br.com.ironcoorp.dogbank.domain.Tutor;
import br.com.ironcoorp.dogbank.exception.StatusPropostaException;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class PropostaCanceladaState implements PropostaState{

    @Enumerated(EnumType.STRING)
    private StatusProposta statusPropostaInter = StatusProposta.CANCELADA;

    @Override
    public void iniciaProposta(Tutor tutor) {
        throw new StatusPropostaException("Nesse momento não podemos atender sua solicitação sua  proposta está cancelada, em breve entraremos em contato com você, aceite ser um Tutor DOGBank :)!!!");
    }

    @Override
    public void complementoProposta(Tutor tutor) {
        throw new StatusPropostaException("Nesse momento não podemos atender sua solicitação sua  proposta está cancelada, em breve entraremos em contato com você, aceite ser um Tutor DOGBank :)!!!");
    }

    @Override
    public void andamentoProposta(Tutor tutor) {
        throw new StatusPropostaException("Nesse momento não podemos atender sua solicitação sua  proposta está cancelada, em breve entraremos em contato com você, aceite ser um Tutor DOGBank :)!!!");
    }

    @Override
    public void aceiteProposta(Tutor tutor) {
        throw new StatusPropostaException("Nesse momento não podemos atender sua solicitação sua  proposta está cancelada, em breve entraremos em contato com você, aceite ser um Tutor DOGBank :)!!!");
    }

    @Override
    public void finalizaProposta(Tutor tutor) {
        throw new StatusPropostaException("Nesse momento não podemos atender sua solicitação sua  proposta está cancelada, em breve entraremos em contato com você, aceite ser um Tutor DOGBank :)!!!");
    }

    @Override
    public void cancelaProposta(Tutor tutor) {
        tutor.setStatusProposta(statusPropostaInter);
    }
}
