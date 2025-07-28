package br.com.smartmed.consultas.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioOutDTO {
    private double totalGeral;
    private List<FaturamentoPorFormaPagamentoDTO> porFormaPagamento;
    private List<FaturamentoPorConvenioDTO> porConvenio;
}