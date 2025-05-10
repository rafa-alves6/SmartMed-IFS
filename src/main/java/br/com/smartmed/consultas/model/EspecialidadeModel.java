package br.com.smartmed.consultas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "especialidade")
public class EspecialidadeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 64)
    @NotNull(message = "Nome da especialidade não pode ser nulo")
    @NotBlank(message = "O nome da especialidade não pode estar em branco")
    private String nome;

    @Column(name = "descricao", nullable = false, length = 255)
    @NotNull(message = "Descrição da especialidade não pode ser nula")
    @NotBlank(message = "Descrição da especialidade não pode estar em branco")
    private String descricao;

    @OneToMany(mappedBy = "especialidade")
    private Set<MedicoModel> medicos = new HashSet<>();
}
