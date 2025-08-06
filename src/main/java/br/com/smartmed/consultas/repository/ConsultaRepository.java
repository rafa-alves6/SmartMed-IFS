package br.com.smartmed.consultas.repository;

import br.com.smartmed.consultas.model.ConsultaModel;
import br.com.smartmed.consultas.rest.dto.faturamento.FaturamentoPorConvenioDTO;
import br.com.smartmed.consultas.rest.dto.faturamento.FaturamentoPorFormaPagamentoDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConsultaRepository extends JpaRepository<ConsultaModel, Long>, JpaSpecificationExecutor<ConsultaModel> {

    @Modifying
    @Transactional
    @Query(
            value = "UPDATE consulta " +
            "SET status = 'CANCELADA', " +
            "observacoes = :motivo " +
            "WHERE id = :consultaId",
            nativeQuery = true
    )
    void cancelarConsulta(
            @Param("consultaId") Long consultaId,
            @Param("motivo") String motivo
    );

    List<ConsultaModel> findAllByPaciente_Cpf(String pacienteCpf);
    List<ConsultaModel> findAllByMedico_NomeContainingIgnoreCase(String medicoNome);
    Optional<ConsultaModel> findByMedico_IdAndDataHoraConsulta(Integer medicoId, LocalDateTime dataHoraConsulta);
    List<ConsultaModel> findAllByDataHoraConsulta(LocalDateTime dataHoraConsulta);
    List<ConsultaModel> findAllByStatusContainingIgnoreCase(String status);
    List<ConsultaModel> findAllByConvenio_NomeContainingIgnoreCase(String convenioNome);
    List<ConsultaModel> findAllByConvenio_Cnpj(String convenioCnpj);
    List<ConsultaModel> findAllByRecepcionista_Id(Integer recepcionistaId);
    List<ConsultaModel> findAllByFormaPagamento_DescricaoContainingIgnoreCase(String formaPagamentoDescricao);
    List<ConsultaModel> findAllByMedico_IdAndDataHoraConsultaBetween(Integer medicoId, LocalDateTime inicio, LocalDateTime fim);
    boolean existsByMedico_IdAndDataHoraConsulta(Integer medicoId, LocalDateTime dataHoraConsulta);


    // Relatório
    @Query("SELECT COALESCE(SUM(csta.valor), 0.0) FROM ConsultaModel csta " +
            "WHERE csta.status = 'REALIZADA' " +
            "AND CAST(csta.dataHoraConsulta AS DATE) BETWEEN :dataInicio AND :dataFim")
    double calcularTotalGeralPorPeriodo(@Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim);

    @Query("SELECT new br.com.smartmed.consultas.rest.dto.faturamento.FaturamentoPorFormaPagamentoDTO(" +
            "pagamento.descricao, COALESCE(SUM(csta.valor), 0.0)) " +
            "FROM ConsultaModel csta " +
            "JOIN csta.formaPagamento pagamento " +
            "WHERE csta.status = 'REALIZADA' " +
            "AND CAST(csta.dataHoraConsulta AS DATE) BETWEEN :dataInicio AND :dataFim " +
            "GROUP BY pagamento.descricao " +
            "ORDER BY pagamento.descricao")
    List<FaturamentoPorFormaPagamentoDTO> buscarFaturamentoPorFormaPagamento(@Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim);

    @Query("SELECT new br.com.smartmed.consultas.rest.dto.faturamento.FaturamentoPorConvenioDTO(" +
            "cnv.nome, COALESCE(SUM(csta.valor), 0.0)) " +
            "FROM ConsultaModel csta " +
            "JOIN csta.convenio cnv " +
            "WHERE csta.status = 'REALIZADA' " +
            "AND csta.convenio IS NOT NULL " +
            "AND CAST(csta.dataHoraConsulta AS DATE) BETWEEN :dataInicio AND :dataFim " +
            "GROUP BY cnv.nome " +
            "ORDER BY cnv.nome")
    List<FaturamentoPorConvenioDTO> buscarFaturamentoPorConvenio(@Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim);
}