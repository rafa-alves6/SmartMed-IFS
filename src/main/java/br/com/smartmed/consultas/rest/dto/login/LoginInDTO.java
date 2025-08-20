package br.com.smartmed.consultas.rest.dto.login;

import lombok.Data;

@Data
public class LoginInDTO {
    private String email;
    private String senha;
}