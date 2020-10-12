package br.com.ironcoorp.dogbank.service;


import br.com.ironcoorp.dogbank.dto.TutorDTO;
import br.com.ironcoorp.dogbank.exception.CPFCadastradoException;
import br.com.ironcoorp.dogbank.repository.TutorRepository;
import org.springframework.stereotype.Service;

@Service
public class VerificaCPFProposta implements AnaliseDados {

    private TutorRepository tutorRepository;

    public VerificaCPFProposta(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    @Override
    public void validaInformacao(TutorDTO tutorDTO) throws CPFCadastradoException {
        if(tutorRepository.findByCpf(tutorDTO.getCpf()).isPresent()){
            throw new CPFCadastradoException(tutorDTO.getCpf());
        }

    }
}
