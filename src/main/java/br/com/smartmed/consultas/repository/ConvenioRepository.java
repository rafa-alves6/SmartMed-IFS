package br.com.smartmed.consultas.repository;

import br.com.smartmed.consultas.model.ConvenioModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConvenioRepository extends JpaRepository<ConvenioModel, Integer> {
    Optional<ConvenioModel> findByNome(String nome);
    Optional<ConvenioModel> findByCnpj(String cnpj);
    List<ConvenioModel> findByStatus(boolean status);

    boolean existsByCnpj(String cnpj);
    boolean existsByStatus(boolean status);
}
