package br.com.smartmed.consultas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "medico")

public class MedicoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome", nullable = false, length = 255)
    @NotNull(message = "O nome do médico não pode ser nulo")
    @NotBlank(message = "O nome do médico não pode ficar em branco")
    private String nome;

    @Column(name = "crm", nullable = false, length = 8)
    @NotNull(message = "O CRM do médico não pode ser nulo")
    @NotBlank(message = "O CRM do médico não pode ficar em branco")
    private String crm;

    @Column(name = "telefone", nullable = false, length = 11)
    @NotNull(message = "O telefone do médico não pode ser nulo")
    @NotBlank(message = "O telefone do médico não pode ficar em branco")
    private String telefone;

    @Column(name = "email", nullable = false, length = 64)
    @NotNull(message = "O email do médico não pode ser nulo")
    @NotBlank(message = "O email do médico não pode ficar em branco")
    private String email;

    @Column(name = "valorConsultaReferencia", nullable = false)
    @NotNull(message = "O valor da consulta do médico não pode ser nulo")
    @NotBlank(message = "O valor da consulta do médico não pode ficar em branco")
    private float valorConsultaReferencia;

    @Column(name = "ativo", nullable = false)
    @NotNull(message = "O status do médico não pode ser nulo")
    @NotBlank(message = "O status do médico não pode ficar em branco")
    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "especialidadeID", nullable = false)
    private EspecialidadeModel especialidade;
}
