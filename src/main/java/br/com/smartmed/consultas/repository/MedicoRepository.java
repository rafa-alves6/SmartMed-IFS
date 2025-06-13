package br.com.smartmed.consultas.repository;

import br.com.smartmed.consultas.model.EspecialidadeModel;
import br.com.smartmed.consultas.model.MedicoModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<MedicoModel, Integer> {
    Optional<MedicoModel> findByCrm(String crm);
    Optional<MedicoModel> findByNomeContainingIgnoreCase(String nome);

    List<MedicoModel> findAllByEspecialidade_NomeIgnoreCase(String especialidadeNome);
    boolean existsByCrm(String crm);
    boolean existsByEmail(String email);
}
