package br.com.smartmed.consultas.rest.dto.reagendamento;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ReagendamentoInDTO {
    private Long consultaID;
    private LocalDateTime novaDataHora;
    @NotNull(message = "O motivo do reagendamento deve ser especificado.")
    private String motivo;
}
