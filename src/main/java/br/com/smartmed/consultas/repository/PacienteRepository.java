package br.com.smartmed.consultas.repository;

import br.com.smartmed.consultas.model.PacienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<PacienteModel, Integer> {

    /*Nossas Implementações...*/
    Optional<PacienteModel> findByCpf(String pCpf);

}
