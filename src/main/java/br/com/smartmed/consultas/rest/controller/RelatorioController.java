package br.com.smartmed.consultas.rest.controller;

import br.com.smartmed.consultas.rest.dto.RelatorioOutDTO;
import br.com.smartmed.consultas.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping("/faturamento")
    public ResponseEntity<RelatorioOutDTO> gerarRelatorioFaturamento(@RequestParam("dataInicio")
                                                                     @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                                     LocalDate dataInicio,

                                                                     @RequestParam("dataFim")
                                                                     @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                                     LocalDate dataFim)
    {
        RelatorioOutDTO relatorio = consultaService.gerarRelatorioFaturamento(dataInicio, dataFim);
        return ResponseEntity.status(HttpStatus.OK).body(relatorio);
    }
}