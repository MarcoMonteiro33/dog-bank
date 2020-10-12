package br.com.ironcoorp.dogbank.service;


import br.com.ironcoorp.dogbank.dto.TutorDTO;

public interface AnaliseDados {

    void validaInformacao(TutorDTO tutorDTO) throws RuntimeException;
    }


