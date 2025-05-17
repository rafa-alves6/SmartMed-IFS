package br.com.smartmed.consultas.controller;

import br.com.smartmed.consultas.model.RecepcionistaModel;
import br.com.smartmed.consultas.service.RecepcionistaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recepcionistas")
public class RecepcionistaController {
    private final RecepcionistaService service;

    public RecepcionistaController(RecepcionistaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<RecepcionistaModel>> listarRecepcionistas() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecepcionistaModel> getRecepcionista(@PathVariable Integer id) {
        return ResponseEntity.ok(service.listarPorId(id));
    }

    @PostMapping
    public ResponseEntity<RecepcionistaModel> criarRecepcionista(@RequestBody RecepcionistaModel Recepcionista) {
        service.adicionar(Recepcionista);
        return ResponseEntity.status(201).body(Recepcionista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecepcionistaModel> atualizarRecepcionista(@PathVariable Integer id, @RequestBody RecepcionistaModel novaRecepcionista) {
        novaRecepcionista.setId(id);
        service.alterar(novaRecepcionista);
        return ResponseEntity.ok(novaRecepcionista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRecepcionista(@PathVariable Integer id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
