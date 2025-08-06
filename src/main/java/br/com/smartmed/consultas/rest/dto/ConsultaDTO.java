package br.com.smartmed.consultas.rest.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConsultaDTO {
    private Long id;
    private LocalDateTime dataHoraConsulta;
    private String status;
    private double valor;
    private String observacoes;

    private PacienteDTO paciente;
    private MedicoDTO medico;
    private ConvenioDTO convenio;
    private FormaPagamentoDTO formaPagamento;
    private RecepcionistaDTO recepcionista;
}