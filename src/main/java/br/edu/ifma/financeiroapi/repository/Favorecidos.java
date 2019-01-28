package br.edu.ifma.financeiroapi.repository;

import br.edu.ifma.financeiroapi.domain.Favorecido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Favorecidos extends JpaRepository<Favorecido, Long> {


    Optional<Favorecido> findByDocumento(String documento);
}
