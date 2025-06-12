package br.com.smartmed.consultas.rest.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RecepcionistaDTO {
    private Integer id;
    private String nome;
    private String cpf;
    private Date dataNascimento;
    private Date dataAdmissao;
    private Date dataDemissao;
    private String telefone;
    private String email;
    private boolean ativo;

}
