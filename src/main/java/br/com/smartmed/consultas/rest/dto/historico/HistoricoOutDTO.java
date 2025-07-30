package br.com.smartmed.consultas.rest.dto.historico;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoOutDTO {

    private LocalDateTime dataHoraConsulta;
    private String nomeMedico;
    private String nomeEspecialidade;
    private Float valor;
    private String status;
    private String observacoes;
}