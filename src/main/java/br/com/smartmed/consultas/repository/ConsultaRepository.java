package br.com.smartmed.consultas.repository;

import br.com.smartmed.consultas.model.ConsultaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConsultaRepository extends JpaRepository<ConsultaModel, Long> {

    List<ConsultaModel> findByPaciente_Cpf(String pacienteCpf);

    List<ConsultaModel> findByMedico_Id(Integer medicoId);

    Optional<ConsultaModel> findByMedico_IdAndDataHoraConsulta(Integer medicoId, LocalDateTime dataHoraConsulta);

    List<ConsultaModel> findByDataHoraConsulta(LocalDateTime dataHoraConsulta);

    List<ConsultaModel> findByStatus(String status); // Finalizada, marcada, remarcada...

    List<ConsultaModel> findByConvenio_Nome(String convenioNome);

    List<ConsultaModel> findByConvenio_Id(Integer convenioId);

    List<ConsultaModel> findByRecepcionista_Id(Integer recepcionistaId);

    List<ConsultaModel> findByFormaPagamento_Descricao(String descricao); // Pix, dinheiro, cŕedito etc
}