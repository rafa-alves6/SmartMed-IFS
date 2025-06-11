package br.com.smartmed.consultas.repository;

import br.com.smartmed.consultas.model.ConsultaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConsultaRepository extends JpaRepository<ConsultaModel, Long> {
    List<ConsultaModel> findAllByPaciente_Cpf(String pacienteCpf);
    List<ConsultaModel> findAllByMedico_Id(Integer medicoId);
    Optional<ConsultaModel> findByMedico_IdAndDataHoraConsulta(Integer medicoId, LocalDateTime dataHoraConsulta);
    List<ConsultaModel> findAllByDataHoraConsulta(LocalDateTime dataHoraConsulta);
    List<ConsultaModel> findAllByStatusContainingIgnoreCase(String status); // Finalizada, marcada, remarcada...
    List<ConsultaModel> findAllByConvenio_Nome(String convenioNome);
    List<ConsultaModel> findAllByConvenio_Id(Integer convenioId);
    List<ConsultaModel> findAllByRecepcionista_Id(Integer recepcionistaId);
    List<ConsultaModel> findAllByFormaPagamento_Descricao(String descricao); // Pix, dinheiro, cŕedito etc

    boolean existsByMedico_IdAndDataHoraConsulta(Integer medicoId, LocalDateTime dataHoraConsulta);
}