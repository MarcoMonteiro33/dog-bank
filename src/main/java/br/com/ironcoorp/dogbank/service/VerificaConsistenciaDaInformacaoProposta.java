package br.com.ironcoorp.dogbank.service;


import br.com.ironcoorp.dogbank.dto.TutorDTO;

public interface VerificaConsistenciaDaInformacaoProposta {

    void validaInformacao(TutorDTO tutorDTO) throws RuntimeException;

    }


