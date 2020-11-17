package br.com.fiap.repository;

import br.com.fiap.model.ExecucaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExecucaoRepository extends JpaRepository<ExecucaoModel, Long> {
}
