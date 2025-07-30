package br.com.smartmed.consultas.rest.dto.historico;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class HistoricoInDTO {

    @NotNull(message = "ID do paciente não pode ser nulo.")
    private Integer pacienteId;

    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Integer medicoId;
    private String status;
    private Integer especialidadeId;
}