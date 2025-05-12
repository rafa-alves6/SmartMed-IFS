package br.com.smartmed.consultas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "consulta")
public class ConsultaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dataHoraConsulta", nullable = false)
    @NotNull(message = "Data e hora da consulta não podem ser nulas")
    private LocalDate dataHoraConsulta;

    @Column(name = "status", nullable = false, length = 16)
    @NotNull(message = "Status da consulta não pode ser nulo")
    @NotBlank(message = "Status da consulta não pode ficar em branco")
    private String status;

    @Column(name = "valor", nullable = true) // Pode ser consulta com convênio
    private float valor;

    @Column(name = "observacoes", nullable = true)
    private String observacoes;

// Foreign keys

    @ManyToOne
    @JoinColumn(name = "pacienteID", nullable = false)
    private PacienteModel paciente;

    @ManyToOne
    @JoinColumn(name = "medicoID", nullable = false)
    private MedicoModel medico;

    @ManyToOne
    @JoinColumn(name = "formaPagamentoID", nullable = false)
    private FormaPagamentoModel formaPagamento;

    @ManyToOne
    @JoinColumn(name = "convenioID", nullable = false)
    private ConvenioModel convenio;

    @ManyToOne
    @JoinColumn(name = "recepcionistaID", nullable = false)
    private RecepcionistaModel recepcionista;
}
