package br.com.ironcoorp.dogbank.service;

import br.com.ironcoorp.dogbank.domain.Tutor;
import br.com.ironcoorp.dogbank.dto.response.EtapaResponseDTO;
import br.com.ironcoorp.dogbank.dto.response.PropostaTutorResponseDTO;
import br.com.ironcoorp.dogbank.exception.TutorNotFoundException;
import br.com.ironcoorp.dogbank.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class QuartaEtapaService {


    private String msgAceite = "Será um pazer criar sua Conta Digital, nosso objetivo é te ajudar a manter o cuidado " +
            "financeiro relacionado ao seu Pet. Sendo assim não deixar que nada falte para ele :). Em breve receberá " +
            "um email contendo os dados da sua conta, até mais!!!";


    private String msgRecusa = "Não fica de fora dessa, vamos te dar um tempo para pensar melhor!!!";

    @Autowired
    TutorRepository tutorRepository;

    @Autowired
    PropostaTutorResponseDTO propostaTutorResponseDTO;

    public PropostaTutorResponseDTO processar(Long id){
        return propostaTutorResponseDTO.convertToDTO(validaDadosEtapa(id));
    }

    public EtapaResponseDTO finalizaProposta(Long id, Boolean aceite){

       Tutor tutor = validaDadosEtapa(id);
       if(aceite){
           return processaMensagemRetorno(msgAceite,tutor);
       }
        return processaMensagemRetorno(msgRecusa, tutor);
    }

    public Tutor validaDadosEtapa(Long id){
        return tutorRepository.findById(id).orElseThrow(() -> new TutorNotFoundException(id));
    }

    public EtapaResponseDTO processaMensagemRetorno(String mensagem, Tutor tutor){
        return new EtapaResponseDTO(tutor.getNome()+ " "+mensagem, tutor.getCodigo());
    }



}
