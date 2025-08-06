package br.com.smartmed.consultas.rest.dto.cancelamento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancelarConsultaResponseDTO {
    private String mensagem;
    private String statusAtual;
}