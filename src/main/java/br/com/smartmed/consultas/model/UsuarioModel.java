package br.com.smartmed.consultas.model;

import br.com.smartmed.consultas.rest.dto.login.Cargos;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotNull(message = "Nome não pode ser nulo") @NotBlank(message = "Nome não pode ficar em branco")
    private String nome;

    @Column(nullable = false, unique = true)
    @NotNull(message = "Email não pode ser nulo") @NotBlank(message = "Email não pode ficar em branco")
    private String email;

    @Column(nullable = false)
    @NotNull(message = "Senha não pode ser nula") @NotBlank(message = "Senha não pode ficar em branco")
    @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres")
    private String senha;

    @Column(nullable = false)
    @NotNull(message = "Perfil não pode ser nulo")
    @Enumerated(EnumType.STRING)
    private Cargos perfil;
}
