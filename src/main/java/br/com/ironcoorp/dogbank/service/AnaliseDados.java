package br.com.ironcoorp.dogbank.service;


import br.com.ironcoorp.dogbank.dto.request.PropostaPrimeiraEtapaDTO;

public interface AnaliseDados {

    void validaInformacao(PropostaPrimeiraEtapaDTO propostaPrimeiraEtapaDTO) throws RuntimeException;
    }


