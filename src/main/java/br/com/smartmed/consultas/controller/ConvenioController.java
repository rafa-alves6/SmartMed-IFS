package br.com.smartmed.consultas.controller;


import br.com.smartmed.consultas.model.ConvenioModel;
import br.com.smartmed.consultas.service.ConvenioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/convenios")
public class ConvenioController {
    private final ConvenioService service;

    public ConvenioController(ConvenioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ConvenioModel>> listarConvenios() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConvenioModel> getConvenio(@PathVariable Integer id) {
        return ResponseEntity.ok(service.listarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ConvenioModel> criarConvenio(@RequestBody ConvenioModel Convenio) {
        service.adicionar(Convenio);
        return ResponseEntity.status(201).body(Convenio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConvenioModel> atualizarConvenio(@PathVariable Integer id, @RequestBody ConvenioModel novaConvenio) {
        novaConvenio.setId(id);
        service.alterar(novaConvenio);
        return ResponseEntity.ok(novaConvenio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarConvenio(@PathVariable Integer id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
    
}
