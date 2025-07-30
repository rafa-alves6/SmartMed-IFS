package br.com.smartmed.consultas.rest.dto.agendamento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoAutomaticoOutDTO {

    private Long idConsulta;
    private String mensagem;
    private LocalDateTime dataHoraConsulta;
    private String nomePaciente;
    private String nomeMedico;
    private String especialidadeMedico;
    private Float valor;

}