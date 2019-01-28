package br.edu.ifma.financeiroapi.repository;

import br.edu.ifma.financeiroapi.domain.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Contas extends JpaRepository<Conta, Long> {
}
