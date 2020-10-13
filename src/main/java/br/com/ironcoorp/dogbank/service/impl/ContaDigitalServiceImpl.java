package br.com.ironcoorp.dogbank.service.impl;


import br.com.ironcoorp.dogbank.domain.ContaDigital;
import br.com.ironcoorp.dogbank.domain.Tutor;
import br.com.ironcoorp.dogbank.exception.TutorNotFoundException;
import br.com.ironcoorp.dogbank.repository.TutorRepository;
import br.com.ironcoorp.dogbank.service.ContaDigitalService;
import br.com.ironcoorp.dogbank.utils.GeraDadosBancarios;
import org.springframework.beans.factory.annotation.Autowired;

public class ContaDigitalServiceImpl implements ContaDigitalService {

    @Autowired
    GeraDadosBancarios geraDadosBancarios;

    @Autowired
    TutorRepository tutorRepository;

    @Override
    public ContaDigital gerarConta(Long idTutor) {

        var tutor = validaDadosEtapa(idTutor);

        var dadosBancarios = geraDadosBancarios.gerarDadosBancarios();

        return new ContaDigital(dadosBancarios.getAgencia(), dadosBancarios.getConta(), tutor);
    }

    public Tutor validaDadosEtapa(Long id){
        return tutorRepository.findById(id).orElseThrow(() -> new TutorNotFoundException(id));
    }



}
