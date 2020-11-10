package br.com.ironcoorp.dogbank.repository;

import br.com.ironcoorp.dogbank.domain.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {

    Optional<String> findByEmail(String email);

    Optional<Long> findByCpf(String cpf);
}
