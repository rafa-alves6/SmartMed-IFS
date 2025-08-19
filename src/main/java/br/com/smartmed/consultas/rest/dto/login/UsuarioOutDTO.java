package br.com.smartmed.consultas.rest.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioOutDTO {
    private String mensagem;
    private Integer usuarioID;
}