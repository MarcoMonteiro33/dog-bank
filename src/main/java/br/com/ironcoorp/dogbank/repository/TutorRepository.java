package br.com.ironcoorp.dogbank.repository;

import br.com.ironcoorp.dogbank.domain.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TutorRepository extends JpaRepository<Tutor, Long> {

    Optional<String> findByEmail(String email);

    Optional<Long> findByCpf(String cpf);
}
