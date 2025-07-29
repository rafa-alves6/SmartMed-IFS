package br.com.smartmed.consultas.rest.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AgendamentoAutomaticoInDTO {

    @NotNull(message = "ID do paciente não pode ser nulo.")
    private int pacienteId;

    @NotNull(message = "ID da especialidade não pode ser nulo.")
    private int especialidadeId;

    @NotNull(message = "Horário inicial não pode ser nulo.")
    private LocalDateTime dataHoraInicial;

    @NotNull(message = "Duração da consulta não pode ser nula.")
    private int duracaoConsultaMinutos;

    private int convenioId; // pode ser nulo (particular)

    @NotNull(message = "ID da forma de pagamento não pode ser nulo.")
    private int formaPagamentoId;
}
