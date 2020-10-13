package br.com.ironcoorp.dogbank.service;

import br.com.ironcoorp.dogbank.domain.StatusProposta;
import br.com.ironcoorp.dogbank.domain.Tutor;
import br.com.ironcoorp.dogbank.dto.response.EtapaResponseDTO;
import br.com.ironcoorp.dogbank.dto.response.PropostaTutorResponseDTO;
import br.com.ironcoorp.dogbank.exception.TutorNotFoundException;
import br.com.ironcoorp.dogbank.repository.TutorRepository;
import br.com.ironcoorp.dogbank.utils.GeraDadosBancarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class QuartaEtapaService {


    private String msgAceite = "Será um pazer criar sua Conta Digital, nosso objetivo é te ajudar a manter o cuidado " +
            "financeiro relacionado ao seu Pet. Sendo assim não deixar que nada falte para ele :). Em breve receberá " +
            "um email contendo o resultado da sua solicitação de conta no DOGBanK, até mais!!!";


    private String msgRecusa = "Não fica de fora dessa, vamos te dar um tempo para pensar melhor!!!";

    @Autowired
    TutorRepository tutorRepository;

    @Autowired
    GeraDadosBancarios geraDadosBancarios;

    @Autowired
    PropostaTutorResponseDTO propostaTutorResponseDTO;

    public PropostaTutorResponseDTO processar(Long id){
        var tutor = validaDadosEtapa(id);
        tutor.aceiteProposta();
        //tutor.setStatusProposta(StatusProposta.ACEITE);
        tutorRepository.save(tutor);
        return propostaTutorResponseDTO.convertToDTO(tutor);
    }

    public EtapaResponseDTO finalizaProposta(Long id, Boolean aceite){

       Tutor tutor = validaDadosEtapa(id);
       if(aceite) {
           //tutor.setStatusProposta(StatusProposta.CONCLUIDO);
           tutor.concluiProposta();
           tutorRepository.save(tutor);
           return processaMensagemRetorno(msgAceite, tutor);
       }
        tutor.setStatusProposta(StatusProposta.CANCELADA);
        tutorRepository.save(tutor);

        return processaMensagemRetorno(msgRecusa, tutor);
    }

    public Tutor validaDadosEtapa(Long id){
        return tutorRepository.findById(id).orElseThrow(() -> new TutorNotFoundException(id));
    }

    public EtapaResponseDTO processaMensagemRetorno(String mensagem, Tutor tutor){
        return new EtapaResponseDTO(tutor.getNome()+ " "+mensagem, tutor.getCodigo());
    }



}
