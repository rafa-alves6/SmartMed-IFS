package br.com.smartmed.consultas.rest.dto;

import lombok.Data;

@Data
public class MedicoDTO {
    private Integer id;
    private String nome;
    private String crm;
    private String telefone;
    private String email;
    private float valorConsultaReferencia;
    private boolean ativo;
    private EspecialidadeDTO especialidade;
}
