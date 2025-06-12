 package br.com.smartmed.consultas.repository;

import br.com.smartmed.consultas.model.EspecialidadeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EspecialidadeRepository extends JpaRepository<EspecialidadeModel, Integer> {
    Optional<EspecialidadeModel> findByNomeContainingIgnoreCase(String nomeEspecialidade);
    boolean existsByNomeIgnoreCase(String nomeEspecialidade); // para POST (PRECISA escrever o nome completo)
}