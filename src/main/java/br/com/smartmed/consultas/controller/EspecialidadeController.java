package br.com.smartmed.consultas.controller;

import br.com.smartmed.consultas.model.EspecialidadeModel;
import br.com.smartmed.consultas.service.EspecialidadeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadeController {
    private final EspecialidadeService service;

    public EspecialidadeController(EspecialidadeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<EspecialidadeModel>> listarEspecialidades() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecialidadeModel> getEspecialidade(@PathVariable Integer id) {
        return ResponseEntity.ok(service.listarPorId(id));
    }

    @PostMapping
    public ResponseEntity<EspecialidadeModel> criarEspecialidade(@RequestBody EspecialidadeModel especialidade) {
        service.adicionar(especialidade);
        return ResponseEntity.status(201).body(especialidade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspecialidadeModel> atualizarEspecialidade(@PathVariable Integer id, @RequestBody EspecialidadeModel novaEspecialidade) {
        novaEspecialidade.setId(id);
        service.alterar(novaEspecialidade);
        return ResponseEntity.ok(novaEspecialidade);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEspecialidade(@PathVariable Integer id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}