package br.com.ironcoorp.dogbank.state;

import br.com.ironcoorp.dogbank.domain.StatusProposta;
import br.com.ironcoorp.dogbank.domain.Tutor;
import br.com.ironcoorp.dogbank.exception.StatusPropostaException;
import br.com.ironcoorp.dogbank.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class PropostaAceiteState implements PropostaState{

    @Enumerated(EnumType.STRING)
    private StatusProposta statusPropostaInter = StatusProposta.ACEITE;

    @Autowired
    TutorRepository tutorRepository;

    @Override
    public void iniciaProposta(Tutor tutor) {
        throw new StatusPropostaException("A proposta já está iniciada");
    }

    @Override
    public void complementoProposta(Tutor tutor) {
        throw new StatusPropostaException("Os Dados Complementares já foram enviados");
    }

    @Override
    public void andamentoProposta(Tutor tutor) {
        throw new StatusPropostaException("Os Anexos já foram enviados");
    }

    @Override
    public void aceiteProposta(Tutor tutor) {
        tutor.setStatusProposta(statusPropostaInter);
    }

    @Override
    public void finalizaProposta(Tutor tutor) {
        throw new StatusPropostaException("Ainda não é possível finalizar sua solicitação, mas tenho uma boa notícia, já estamos acabando!!!");
    }

    @Override
    public void cancelaProposta(Tutor tutor) {
        throw new StatusPropostaException("Nesse momento não podemos atender sua solicitação sua  proposta está cancelada, em breve entraremos em contato com você, aceite ser um Tutor DOGBank :)!!!");
    }

}
