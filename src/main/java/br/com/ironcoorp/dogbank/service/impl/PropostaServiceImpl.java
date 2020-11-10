package br.com.ironcoorp.dogbank.service.impl;

import br.com.ironcoorp.dogbank.domain.Tutor;
import br.com.ironcoorp.dogbank.repository.TutorRepository;
import br.com.ironcoorp.dogbank.service.PropostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class PropostaServiceImpl implements PropostaService {

    @Autowired
    TutorRepository tutorRepository;


    @Override
    public Page<Tutor> findAll(Pageable pageable) {
        return tutorRepository.findAll(pageable);
    }
}
