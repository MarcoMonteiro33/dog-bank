package br.com.ironcoorp.dogbank.repository;

import br.com.ironcoorp.dogbank.domain.ContaDigital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaDigitalRepository extends JpaRepository<ContaDigital, Long> {

    Boolean findByAgenciaAndConta(String ag, String conta);


}
