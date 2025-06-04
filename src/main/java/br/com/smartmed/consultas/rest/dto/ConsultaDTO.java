package br.com.smartmed.consultas.rest.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class ConsultaDTO {
    private Long id;
    private LocalDate dataHoraConsulta;
    private String status;
    private float valor;
    private String observacoes;

}
