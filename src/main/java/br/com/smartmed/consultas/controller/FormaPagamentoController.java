package br.com.smartmed.consultas.controller;

import br.com.smartmed.consultas.model.FormaPagamentoModel;
import br.com.smartmed.consultas.service.FormaPagamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formaPagamento")
public class FormaPagamentoController {
    private final FormaPagamentoService service;

    public FormaPagamentoController(FormaPagamentoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<FormaPagamentoModel>> listarFormaPagamentos() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormaPagamentoModel> getFormaPagamento(@PathVariable Integer id) {
        return ResponseEntity.ok(service.listarPorId(id));
    }

    @PostMapping
    public ResponseEntity<FormaPagamentoModel> criarFormaPagamento(@RequestBody FormaPagamentoModel FormaPagamento) {
        service.adicionar(FormaPagamento);
        return ResponseEntity.status(201).body(FormaPagamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormaPagamentoModel> atualizarFormaPagamento(@PathVariable Integer id, @RequestBody FormaPagamentoModel novaFormaPagamento) {
        novaFormaPagamento.setId(id);
        service.alterar(novaFormaPagamento);
        return ResponseEntity.ok(novaFormaPagamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFormaPagamento(@PathVariable Integer id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
