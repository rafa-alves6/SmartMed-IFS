package br.com.smartmed.consultas.model;

import br.com.smartmed.consultas.rest.dto.ConvenioDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "convenio")
public class ConvenioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", length = 255, nullable = false)
    @NotNull(message = "Nome do convênio não pode ser nulo")
    @NotBlank(message = "Nome do convênio não pode ficar em branco")
    private String nome;

    @Column(name = "cnpj", length = 14, nullable = false, unique = true)
    @CNPJ(message = "CNPJ Inválido")
    private String cnpj;

    @Column(name = "telefone", length = 11, nullable = false)
    @Length(min = 11, max = 11, message = "O campo deve ter exatamente 11 números. Ex: 79981130366")
    @NotNull(message = "O telefone não pode ser nulo.")
    @NotBlank(message = "O telefone é obrigatório.")
    private String telefone;

    @Column(name = "email", length = 64, nullable = false, unique = true)
    @NotNull(message = "O email não pode ser nulo")
    @NotBlank(message = "O email não pode ficar em branco")
    private String email;

    @Column(name = "status", nullable = false)
    @NotNull(message = "Status do convenio não pode ser nulo")
    private boolean status;

    public ConvenioDTO toDTO () {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, ConvenioDTO.class);
    }
}
