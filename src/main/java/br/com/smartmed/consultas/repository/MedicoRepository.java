package br.com.smartmed.consultas.repository;

import br.com.smartmed.consultas.model.MedicoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<MedicoModel, Integer> {
    Optional<MedicoModel> findByCrm(String crm);

    boolean existsByCrm(String pCrm);

    boolean existsByNome(String pNome);

    Optional<MedicoModel> findByEspecialidadeNome(String pEspecialidadeNome);

}
