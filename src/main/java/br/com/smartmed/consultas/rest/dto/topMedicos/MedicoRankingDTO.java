package br.com.smartmed.consultas.rest.dto.topMedicos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicoRankingDTO {
    private String nomeMedico;
    private Long quantidadeConsultas;
}
