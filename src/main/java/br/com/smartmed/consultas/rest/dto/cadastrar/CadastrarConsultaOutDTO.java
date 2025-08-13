package br.com.smartmed.consultas.rest.dto.cadastrar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CadastrarConsultaOutDTO {
    private String mensagem;
    private String status;
}
