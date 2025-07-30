package br.com.smartmed.consultas.rest.dto.agendaMedica;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendaOutDTO {

    private String medico;
    private LocalDate data;
    private List<String> horariosOcupados;
    private List<String> horariosDisponiveis;
}