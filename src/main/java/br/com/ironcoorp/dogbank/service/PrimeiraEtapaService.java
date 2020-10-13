package br.com.ironcoorp.dogbank.service;

import br.com.ironcoorp.dogbank.domain.Tutor;
import br.com.ironcoorp.dogbank.dto.request.PropostaPrimeiraEtapaDTO;
import br.com.ironcoorp.dogbank.dto.response.EtapaResponseDTO;
import br.com.ironcoorp.dogbank.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrimeiraEtapaService {

    @Autowired
    VerificaIdadePermitida analiseIdade;

    @Autowired
    VerificaCPFProposta analiseCPF;

    @Autowired
    VerificaEmailProposta analiseEmail;

    @Autowired
    TutorRepository tutorRepository;

    public EtapaResponseDTO processa(PropostaPrimeiraEtapaDTO propostaPrimeiraEtapaDTO){

        analiseIdade.validaInformacao(propostaPrimeiraEtapaDTO);

        analiseCPF.validaInformacao(propostaPrimeiraEtapaDTO);

        analiseEmail.validaInformacao(propostaPrimeiraEtapaDTO);

        Tutor tutor = PropostaPrimeiraEtapaDTO.convertToTutor(propostaPrimeiraEtapaDTO);

        tutor.iniciaProposta();

        return processaMensagemRetorno(tutorRepository.save(tutor).getCodigo());

    }

    public EtapaResponseDTO processaMensagemRetorno(Long id){
        return new EtapaResponseDTO("Dados enviados com Sucesso!", id);
    }
}
