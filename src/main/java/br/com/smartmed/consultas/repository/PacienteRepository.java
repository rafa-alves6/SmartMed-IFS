package br.com.smartmed.consultas.repository;

import br.com.smartmed.consultas.model.PacienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<PacienteModel, Integer> {
}
