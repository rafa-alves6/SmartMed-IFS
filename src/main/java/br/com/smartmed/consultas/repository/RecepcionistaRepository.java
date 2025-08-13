package br.com.smartmed.consultas.repository;

import br.com.smartmed.consultas.model.RecepcionistaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RecepcionistaRepository extends JpaRepository<RecepcionistaModel, Integer> {
    Optional<RecepcionistaModel> findByCpf(String cpf);
    List<RecepcionistaModel> findAllByNomeContainingIgnoreCase(String nome);
    List<RecepcionistaModel> findAllByAtivo(boolean ativo);

    boolean existsByCpf(String cpf);

    @Query(value = "SELECT * FROM recepcionista r " +
            "WHERE (:ativo IS NULL OR r.ativo = :ativo) " +
            "AND (:dataInicio IS NULL OR r.data_admissao >= :dataInicio) " +
            "AND (:dataFim IS NULL OR r.data_admissao <= :dataFim)",
            countQuery = "SELECT count(*) FROM recepcionista r " +
                    "WHERE (:ativo IS NULL OR r.ativo = :ativo) " +
                    "AND (:dataInicio IS NULL OR r.data_admissao >= :dataInicio) " +
                    "AND (:dataFim IS NULL OR r.data_admissao <= :dataFim)",
            nativeQuery = true)
    Page<RecepcionistaModel> filtrar(
            @Param("ativo") boolean ativo,
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim,
            Pageable pageable
    );
}