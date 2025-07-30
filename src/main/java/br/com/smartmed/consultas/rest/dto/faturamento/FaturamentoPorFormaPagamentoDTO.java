package br.com.smartmed.consultas.rest.dto.faturamento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FaturamentoPorFormaPagamentoDTO {
    private String formaPagamento;
    private double valor;
}