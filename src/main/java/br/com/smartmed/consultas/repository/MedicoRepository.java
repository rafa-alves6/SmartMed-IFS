package br.com.smartmed.consultas.repository;

import br.com.smartmed.consultas.model.EspecialidadeModel;
import br.com.smartmed.consultas.model.MedicoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<MedicoModel, Integer> {
    Optional<MedicoModel> findByCrm(String crm);
    List<MedicoModel> findAllByNomeContainingIgnoreCase(String nome);
    List<MedicoModel> findAllByEspecialidade(EspecialidadeModel especialidade);
    boolean existsByCrm(String crm);
    boolean existsByEmail(String email);
}
