package br.com.ironcoorp.dogbank.service;


import br.com.ironcoorp.dogbank.domain.Endereco;
import br.com.ironcoorp.dogbank.domain.StatusProposta;
import br.com.ironcoorp.dogbank.domain.Tutor;
import br.com.ironcoorp.dogbank.dto.TutorSegundaEtapaDTO;
import br.com.ironcoorp.dogbank.exception.TutorNotFoundException;
import br.com.ironcoorp.dogbank.repository.TutorRepository;
import org.springframework.stereotype.Service;

@Service
public class SegundaEtapaService {

    private TutorRepository tutorRepository;

    public SegundaEtapaService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    public Tutor processar(TutorSegundaEtapaDTO tutorSegundaEtapaDTO, Long id){

        Tutor tutor = tutorRepository.findById(id).orElseThrow(() -> new TutorNotFoundException(id));
        tutor.setStatusProposta(StatusProposta.ANDAMENTO);

        tutor.setEndereco(Endereco.Builder
                .newBuilder()
                .cep(tutorSegundaEtapaDTO.getCep())
                .rua(tutorSegundaEtapaDTO.getRua())
                .complemento(tutorSegundaEtapaDTO.getComplemento())
                .bairro(tutorSegundaEtapaDTO.getBairro())
                .cidade(tutorSegundaEtapaDTO.getCidade())
                .uf(tutorSegundaEtapaDTO.getUf())
                .build()
        );

        return tutorRepository.save(tutor);
    }

}
