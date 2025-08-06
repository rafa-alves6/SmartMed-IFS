package br.com.smartmed.consultas.rest.dto.cancelamento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CancelarConsultaDTO {

    @NotNull(message = "O ID da consulta é obrigatório.")
    private Long consultaID;

    @NotBlank(message = "O motivo do cancelamento é obrigatório.")
    private String motivo;
}