package br.com.smartmed.consultas.rest.dto.filtrar;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

@Data
public class ListarRecepcionistaDTO {
    private String status;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Integer pagina = 0;
    private Integer tamanhoPagina = 5;

    public Pageable toPageable() {
        return PageRequest.of(this.pagina, this.tamanhoPagina);
    }
}