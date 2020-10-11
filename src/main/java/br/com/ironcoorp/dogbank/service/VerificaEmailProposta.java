package br.com.ironcoorp.dogbank.service;

import br.com.ironcoorp.dogbank.dto.TutorDTO;
import br.com.ironcoorp.dogbank.exception.EmailCadastradorException;
import br.com.ironcoorp.dogbank.repository.TutorRepository;
import org.springframework.stereotype.Service;

@Service
public class VerificaEmailProposta implements VerificaConsistenciaDaInformacaoProposta {

    private final TutorRepository tutorRepository;

    public VerificaEmailProposta(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    @Override
    public void validaInformacao(TutorDTO tutorDTO) throws EmailCadastradorException {
        if(tutorRepository.findByEmail(tutorDTO.getEmail()).isPresent()){
            throw new EmailCadastradorException(tutorDTO.getEmail());
        }

    }
}
