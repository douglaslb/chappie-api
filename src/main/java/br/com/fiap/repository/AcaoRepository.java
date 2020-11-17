package br.com.fiap.repository;

import br.com.fiap.model.AcaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AcaoRepository extends JpaRepository<AcaoModel, Long> {
}
