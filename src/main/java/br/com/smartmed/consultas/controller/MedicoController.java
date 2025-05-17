package br.com.smartmed.consultas.controller;

import br.com.smartmed.consultas.model.MedicoModel;
import br.com.smartmed.consultas.service.MedicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    private final MedicoService service;

    public MedicoController(MedicoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<MedicoModel>> listarMedicos() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoModel> getMedico(@PathVariable Integer id) {
        return ResponseEntity.ok(service.listarPorId(id));
    }

    @PostMapping
    public ResponseEntity<MedicoModel> criarMedico(@RequestBody MedicoModel medico) {
        service.adicionar(medico);
        return ResponseEntity.status(201).body(medico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoModel> atualizarMedico(@PathVariable Integer id, @RequestBody MedicoModel novoMedico) {
        novoMedico.setId(id);
        service.alterar(novoMedico);
        return ResponseEntity.ok(novoMedico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMedico(@PathVariable Integer id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
