package br.com.smartmed.consultas.repository;

import br.com.smartmed.consultas.model.MedicoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<MedicoModel, Integer> {
}
