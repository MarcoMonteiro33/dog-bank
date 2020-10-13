package br.com.ironcoorp.dogbank.service;


import br.com.ironcoorp.dogbank.dto.request.PropostaPrimeiraEtapaDTO;
import br.com.ironcoorp.dogbank.exception.CPFCadastradoException;
import br.com.ironcoorp.dogbank.repository.TutorRepository;
import org.springframework.stereotype.Component;

@Component
public class VerificaCPFProposta implements AnaliseDados {

    private TutorRepository tutorRepository;

    public VerificaCPFProposta(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    @Override
    public void validaInformacao(PropostaPrimeiraEtapaDTO propostaPrimeiraEtapaDTO) throws CPFCadastradoException {
        if(tutorRepository.findByCpf(propostaPrimeiraEtapaDTO.getCpf()).isPresent()){
            throw new CPFCadastradoException(propostaPrimeiraEtapaDTO.getCpf());
        }

    }
}
