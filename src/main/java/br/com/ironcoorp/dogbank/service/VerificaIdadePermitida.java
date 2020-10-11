package br.com.ironcoorp.dogbank.service;


import br.com.ironcoorp.dogbank.dto.TutorDTO;
import br.com.ironcoorp.dogbank.exception.IdadeNaoPermitidaException;

import java.time.LocalDate;
import java.time.Period;

public class VerificaIdadePermitida implements VerificaConsistenciaDaInformacaoProposta{

    @Override
    public void validaInformacao(TutorDTO tutorDTO) throws RuntimeException {
        int idade = Period.between(tutorDTO.getDataNascimento(), LocalDate.now()).getYears();
        if (idade < 18){
            throw new IdadeNaoPermitidaException(Long.valueOf(idade));
        }
    }

}
