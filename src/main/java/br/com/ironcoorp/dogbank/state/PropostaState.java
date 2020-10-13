package br.com.ironcoorp.dogbank.state;

import br.com.ironcoorp.dogbank.domain.Tutor;

public interface PropostaState {

    void iniciaProposta(Tutor tutor);

    void complementoProposta(Tutor tutor);

    void andamentoProposta(Tutor tutor);

    void aceiteProposta(Tutor tutor);

    void finalizaProposta(Tutor tutor);

    void cancelaProposta(Tutor tutor);

}
