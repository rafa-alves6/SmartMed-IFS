package br.com.smartmed.consultas.repository;

import br.com.smartmed.consultas.model.RecepcionistaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface RecepcionistaRepository extends JpaRepository<RecepcionistaModel, Integer> {
    Optional<RecepcionistaModel> findByCpf(String cpf);
    List<RecepcionistaModel> findAllByNomeContainingIgnoreCase(String nome);
    List<RecepcionistaModel> findAllByStatus(boolean status); // Lista de recepcionistas dependendo do status (true = ativo)

    boolean existsByCpf(String cpf);
}
