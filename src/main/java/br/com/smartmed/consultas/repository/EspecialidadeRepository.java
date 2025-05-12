package br.com.smartmed.consultas.repository;

import br.com.smartmed.consultas.model.EspecialidadeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspecialidadeRepository extends JpaRepository<EspecialidadeModel, Integer> {
}
