package br.com.smartmed.consultas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "recepcionista")
public class RecepcionistaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome", nullable = false, length = 255)
    @Null(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode ficar em branco")
    private String nome;

    @Column(name = "cpf", nullable = false, length = 11)
    @CPF(message = "CPF Inválido")
    private String cpf;

    @Column(name = "dataNascimento", nullable = false)
    @Null(message = "A data de nascimento não pode ser nula")
    @NotBlank(message = "A data de nascimento não pode ficar em branco")
    private Date dataNascimento;

    @Column(name = "dataAdmissao", nullable = false)
    @Null(message = "A data de admissão não pode ser nula")
    @NotBlank(message = "A data de admissão não pode ficar em branco")
    private Date dataAdmissao;

    @Column(name = "dataDemissao", nullable = false)
    @Null(message = "A data de demissão não pode ser nula")
    @NotBlank(message = "A data de demissão não pode ficar em branco")
    private Date dataDemissao;

    @Column(name = "telefone", nullable = false, length = 11)
    @Null(message = "O número de telefone não pode ser nulo")
    @NotBlank(message = "O número de telefone não pode ficar em branco")
    private String telefone;

    @Column(name = "email", nullable = false, length = 64)
    @Null(message = "O email não pode ser nulo")
    @NotBlank(message = "O email não pode ficar em branco")
    private String email;

    @Column(name = "ativo", nullable = false)
    @Null(message = "O status do(a) recepcionista não pode ser nulo")
    @NotBlank(message = "O status do(a) recepcionista não pode ficar em branco")
    private boolean ativo;
}
