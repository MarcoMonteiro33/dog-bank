package br.com.ironcoorp.dogbank.service;

import br.com.ironcoorp.dogbank.dto.request.PropostaPrimeiraEtapaDTO;
import br.com.ironcoorp.dogbank.exception.EmailCadastradorException;
import br.com.ironcoorp.dogbank.repository.TutorRepository;
import org.springframework.stereotype.Service;

@Service
public class VerificaEmailProposta implements AnaliseDados {

    private final TutorRepository tutorRepository;

    public VerificaEmailProposta(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    @Override
    public void validaInformacao(PropostaPrimeiraEtapaDTO propostaPrimeiraEtapaDTO) throws EmailCadastradorException {
        if(tutorRepository.findByEmail(propostaPrimeiraEtapaDTO.getEmail()).isPresent()){
            throw new EmailCadastradorException(propostaPrimeiraEtapaDTO.getEmail());
        }

    }
}
