package br.com.ironcoorp.dogbank.service;

import br.com.ironcoorp.dogbank.domain.Tutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public interface PropostaService {

    Page<Tutor> findAll(Pageable pageable);

}
