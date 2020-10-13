package br.com.ironcoorp.dogbank.utils;

import br.com.ironcoorp.dogbank.domain.StatusProposta;
import br.com.ironcoorp.dogbank.exception.StatusPropostaException;
import br.com.ironcoorp.dogbank.state.*;

public class GeraInstanciaStatus {

    public static PropostaState criaInstancia(StatusProposta statusProposta){

            if(statusProposta.equals(StatusProposta.INICIADA)){
                return new PropostaComplementoState();
            }else if(statusProposta.equals(StatusProposta.COMPLEMENTO)){
                return new PropostaAndamentoState();
            }else if(statusProposta.equals(StatusProposta.ANDAMENTO)){
                return new PropostaAceiteState();
            }else if(statusProposta.equals(StatusProposta.ACEITE)){
                return new PropostaConluidaState();
            }else if(statusProposta.equals(StatusProposta.CONCLUIDO)){
                return new PropostaConluidaState();
            }else if(statusProposta.equals(StatusProposta.CANCELADA)){
                return new PropostaCanceladaState();
            }

            return (PropostaState) new StatusPropostaException("Não Encontramos sua Proposta.");
        }
    }

