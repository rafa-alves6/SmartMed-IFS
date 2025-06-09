package br.com.smartmed.consultas.repository;

import br.com.smartmed.consultas.model.MedicoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<MedicoModel, Integer> {
    Optional<MedicoModel> findByCrm(String crm);
    Optional<MedicoModel> findByNome(String nome);
    List<MedicoModel> findAllByEspecialidade_Nome(String especialidadeNome); // Lista de médicos com uma certa especialidade (ex: Cardiologia, Oncologia..)

    boolean existsByCrm(String crm);
}
