package br.com.ironcoorp.dogbank.service;


import br.com.ironcoorp.dogbank.domain.Endereco;
import br.com.ironcoorp.dogbank.domain.Tutor;
import br.com.ironcoorp.dogbank.dto.request.PropostaSegundaEtapaDTO;
import br.com.ironcoorp.dogbank.dto.response.EtapaResponseDTO;
import br.com.ironcoorp.dogbank.exception.TutorNotFoundException;
import br.com.ironcoorp.dogbank.repository.TutorRepository;
import org.springframework.stereotype.Service;

@Service
public class SegundaEtapaService {

    private TutorRepository tutorRepository;

    public SegundaEtapaService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    public EtapaResponseDTO processar(PropostaSegundaEtapaDTO propostaSegundaEtapaDTO, Long id){

        Tutor tutor = tutorRepository.findById(id).orElseThrow(() -> new TutorNotFoundException(id));
        tutor.setEndereco(Endereco.Builder
                .newBuilder()
                .cep(propostaSegundaEtapaDTO.getCep())
                .rua(propostaSegundaEtapaDTO.getRua())
                .complemento(propostaSegundaEtapaDTO.getComplemento())
                .bairro(propostaSegundaEtapaDTO.getBairro())
                .cidade(propostaSegundaEtapaDTO.getCidade())
                .uf(propostaSegundaEtapaDTO.getUf())
                .build()
        );


        tutor.complementoProposta();

        return processaMensagemRetorno(tutorRepository.save(tutor).getCodigo());
    }

    public EtapaResponseDTO processaMensagemRetorno(Long id){
        return new EtapaResponseDTO("Dados enviados com Sucesso!", id);
    }

}
