package br.com.smartmed.consultas.model;

import br.com.smartmed.consultas.rest.dto.RecepcionistaDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "recepcionista")
public class RecepcionistaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 255)
    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode ficar em branco")
    private String nome;

    @Column(name = "cpf", nullable = false, length = 11)
    @CPF(message = "CPF Inválido")
    private String cpf;

    @Column(name = "dataNascimento", nullable = false)
    @NotNull(message = "A data de nascimento não pode ser nula")
    private LocalDate dataNascimento;

    @Column(name = "dataAdmissao", nullable = false)
    @NotNull(message = "A data de admissão não pode ser nula")
    private LocalDate dataAdmissao;

    @Column(name = "dataDemissao", nullable = true)
    private LocalDate dataDemissao;

    @Column(name = "telefone", nullable = false, length = 11)
    @NotNull(message = "O número de telefone não pode ser nulo")
    @NotBlank(message = "O número de telefone não pode ficar em branco")
    private String telefone;

    @Column(name = "email", nullable = false, length = 64)
    @NotNull(message = "O email não pode ser nulo")
    @NotBlank(message = "O email não pode ficar em branco")
    private String email;

    @Column(name = "status", nullable = false)
    @NotNull(message = "O status do(a) recepcionista não pode ser nulo")
    private boolean status;

    @OneToMany(mappedBy = "recepcionista")
    private Set<ConsultaModel> consultas = new HashSet<>();

}
