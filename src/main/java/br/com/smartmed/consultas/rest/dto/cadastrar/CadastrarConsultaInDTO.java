package br.com.smartmed.consultas.rest.dto.cadastrar;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CadastrarConsultaInDTO {

    @NotNull(message = "A data e hora da consulta são obrigatórias.")
    private LocalDateTime dataHora;

    @NotNull(message = "O ID do paciente é obrigatório.")
    private Integer pacienteID;

    @NotNull(message = "O ID do médico é obrigatório.")
    private Integer medicoID;

    private Integer convenioID; // Opcional

    @NotNull(message = "A forma de pagamento é obrigatória.")
    private Integer formaPagamentoID;

    @NotNull(message = "O ID do recepcionista é obrigatório.")
    private Integer recepcionistaID;
}