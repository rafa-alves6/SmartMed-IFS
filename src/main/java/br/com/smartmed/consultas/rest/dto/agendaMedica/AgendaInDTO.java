package br.com.smartmed.consultas.rest.dto.agendaMedica;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class AgendaInDTO {

    @NotNull(message = "O ID do médico é obrigatório.")
    private Integer medicoId;

    @NotNull(message = "A data é obrigatória.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate data;
}