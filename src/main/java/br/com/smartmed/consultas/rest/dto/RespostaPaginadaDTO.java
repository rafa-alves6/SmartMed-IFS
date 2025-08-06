package br.com.smartmed.consultas.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespostaPaginadaDTO<T> {
    private List<T> conteudo;
    private Integer totalPaginas;
    private Integer paginaAtual;

    public static <T> RespostaPaginadaDTO<T> fromPage(Page<T> page) {
        return new RespostaPaginadaDTO<>(page.getContent(), page.getTotalPages(), page.getNumber());
    }
}