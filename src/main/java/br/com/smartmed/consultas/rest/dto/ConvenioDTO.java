package br.com.smartmed.consultas.rest.dto;

import lombok.Data;

@Data
public class ConvenioDTO {
    private Integer id;
    private String nome;
    private String cnpj;
    private String telefone;
    private String email;
    private boolean ativo;
}
