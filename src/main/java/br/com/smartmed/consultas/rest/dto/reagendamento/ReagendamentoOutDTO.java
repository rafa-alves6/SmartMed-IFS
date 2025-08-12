package br.com.smartmed.consultas.rest.dto.reagendamento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReagendamentoOutDTO {
    private String mensagem;
    private LocalDateTime novaDataHora;
}
