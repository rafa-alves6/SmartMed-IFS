package br.com.smartmed.consultas.rest.dto.relatorio;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RelatorioInDTO {

    @NotNull(message = "Data de início não pode ser nula")
    private LocalDate dataInicio;

    @NotNull(message = "Data fim não pode ser nula")
    private LocalDate dataFim;
}
