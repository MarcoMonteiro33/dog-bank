package br.com.ironcoorp.dogbank.service;


import br.com.ironcoorp.dogbank.dto.request.PropostaPrimeiraEtapaDTO;
import br.com.ironcoorp.dogbank.exception.IdadeNaoPermitidaException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class VerificaIdadePermitida implements AnaliseDados {

    @Override
    public void validaInformacao(PropostaPrimeiraEtapaDTO propostaPrimeiraEtapaDTO) throws RuntimeException {
        int idade = Period.between(propostaPrimeiraEtapaDTO.getDataNascimento(), LocalDate.now()).getYears();
        if (idade < 18){
            throw new IdadeNaoPermitidaException(Long.valueOf(idade));
        }
    }


}
