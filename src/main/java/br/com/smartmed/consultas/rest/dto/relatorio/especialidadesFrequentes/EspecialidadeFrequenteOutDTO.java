package br.com.smartmed.consultas.rest.dto.relatorio.especialidadesFrequentes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EspecialidadeFrequenteOutDTO {
    private String especialidade;
    private Long quantidade;
}