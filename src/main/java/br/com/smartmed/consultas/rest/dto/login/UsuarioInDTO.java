package br.com.smartmed.consultas.rest.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioInDTO {
    private String nome;
    private String email;
    private String senha;
    private Cargos perfil;
}
