package br.com.smartmed.consultas.rest.dto.relatorio.faturamento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FaturamentoPorConvenioDTO {
    private String convenio;
    private double valor;
}