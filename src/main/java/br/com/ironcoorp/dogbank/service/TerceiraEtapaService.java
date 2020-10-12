package br.com.ironcoorp.dogbank.service;


import br.com.ironcoorp.dogbank.domain.Documento;
import br.com.ironcoorp.dogbank.domain.Tutor;
import br.com.ironcoorp.dogbank.exception.TutorNotFoundException;
import br.com.ironcoorp.dogbank.repository.DocumentoRepository;
import br.com.ironcoorp.dogbank.repository.TutorRepository;
import br.com.ironcoorp.dogbank.utils.Disco;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class TerceiraEtapaService {

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private Disco disco;


    public Tutor processar(Long id, MultipartFile file){

        if(file == null){
            throw new IllegalArgumentException("Upload de arquivo Obrigatorio!!");
        }

        Tutor tutor = validaDadosEtapa(id);

        tutor.setDocumento(criaUploadDocumento(file, id));

        return tutorRepository.save(tutor);
    }

    public Tutor validaDadosEtapa(Long id){

        return tutorRepository.findById(id).orElseThrow(() -> new TutorNotFoundException(id));
    }

    public Documento criaUploadDocumento(MultipartFile file, Long id){

        String documentoCriado = disco.salvar(file);

        return  documentoRepository.save( new Documento("PROPOSTA_TUTOR_"+id,documentoCriado));
    }
}
