package br.com.smartmed.consultas.model;

import br.com.smartmed.consultas.rest.dto.FormaPagamentoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "formaPagamento")
public class FormaPagamentoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "descricao", nullable = false, length = 64)
    @NotNull(message = "Descrição do pagamento não pode ser nula")
    @NotBlank(message = "Descrição do pagamento não pode estar em branco")
    private String descricao;

    public FormaPagamentoDTO toDTO () {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, FormaPagamentoDTO.class);
    }
}
