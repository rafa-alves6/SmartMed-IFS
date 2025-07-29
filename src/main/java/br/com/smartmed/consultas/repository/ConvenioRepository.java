package br.com.smartmed.consultas.repository;

import br.com.smartmed.consultas.model.ConvenioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConvenioRepository extends JpaRepository<ConvenioModel, Integer> {
    Optional<ConvenioModel> findByNomeContainingIgnoreCase(String nome);
    Optional<ConvenioModel> findByCnpj(String cnpj);
    List<ConvenioModel> findAllByAtivo(boolean ativo);
    boolean existsByCnpj(String cnpj);
}
