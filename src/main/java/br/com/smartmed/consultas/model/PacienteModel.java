package br.com.smartmed.consultas.model;


import br.com.smartmed.consultas.rest.dto.PacienteDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
<<<<<<< HEAD
=======
>>>>>>> a999726 (add)

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "paciente")
public class PacienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
<<<<<<< HEAD
    private Integer id;
=======
    private int id;
>>>>>>> a999726 (add)

    @Column(name = "nome", length = 255, nullable = false)
    @NotNull(message = "O nome não pode ser nulo.")
    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @Column(name = "cpf", length = 11, nullable = false, unique = true)
    @CPF(message = "CPF inválido.")
    private String cpf;

    @Column(name = "dataNascimento", nullable = false)
<<<<<<< HEAD
    @NotNull(message = "É preciso informar a data de nascimento")
=======
>>>>>>> a999726 (add)
    private LocalDate dataNascimento;

    @Column(name = "telefone", length = 11, nullable = false)
    @Length(min = 11, max = 11, message = "O campo deve ter exatamente 11 números. Ex: 79981130366")
    @NotNull(message = "O telefone não pode ser nulo.")
    @NotBlank(message = "O telefone é obrigatório.")
    private String telefone;

    @Column(name = "email", length = 64, nullable = true)
    @Email(message = "E-mail inválido.")
    private String email;

<<<<<<< HEAD
    @OneToMany(mappedBy = "paciente")

    public PacienteDTO toDTO () {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, PacienteDTO.class);
    }
}
=======
    public PacienteDTO toDTO(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, PacienteDTO.class);
    }
}
>>>>>>> a999726 (add)
