package br.com.smartmed.consultas.rest.dto.topMedicos;

import lombok.Data;

@Data
public class TopMedicosOutDTO {
    private String nomeMedico;
    private int consultas;
}
