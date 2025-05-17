package br.com.smartmed.consultas.controller;


import br.com.smartmed.consultas.model.ConsultaModel;
import br.com.smartmed.consultas.service.ConsultaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    private final ConsultaService service;
    public ConsultaController(ConsultaService service) {
        this.service=service;
    }

    @GetMapping
    public ResponseEntity<List<ConsultaModel>> listarConsultas() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaModel> getConsulta(@PathVariable Long id) {
        return ResponseEntity.ok(service.listarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ConsultaModel> criarConsulta(@RequestBody ConsultaModel consulta) {
        service.adicionar(consulta);
        return ResponseEntity.status(201).body(consulta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaModel> atualizarConsulta(@PathVariable Long id, @RequestBody ConsultaModel novaConsulta) {
        novaConsulta.setId(id);
        service.alterar(novaConsulta);
        return ResponseEntity.ok(novaConsulta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ConsultaModel> deletarConsulta(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
