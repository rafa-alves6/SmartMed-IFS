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

    @OneToMany(mappedBy = "formaPagamento")
    private Set<ConsultaModel> consultas = new HashSet<>();

}
