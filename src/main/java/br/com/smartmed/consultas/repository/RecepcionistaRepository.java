package br.com.smartmed.consultas.repository;

import br.com.smartmed.consultas.model.RecepcionistaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecepcionistaRepository extends JpaRepository<RecepcionistaModel, Integer> {
}
