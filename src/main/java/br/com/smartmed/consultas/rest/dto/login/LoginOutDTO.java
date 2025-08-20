package br.com.smartmed.consultas.rest.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginOutDTO {
    private String mensagem;
    private UsuarioInfo usuario;
    private String token;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UsuarioInfo {
        private Integer id;
        private String nome;
        private Cargos perfil;
    }
}