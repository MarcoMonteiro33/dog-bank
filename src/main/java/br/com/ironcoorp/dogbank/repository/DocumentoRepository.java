package br.com.ironcoorp.dogbank.repository;

import br.com.ironcoorp.dogbank.domain.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {
}
