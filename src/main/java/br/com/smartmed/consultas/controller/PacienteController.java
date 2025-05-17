package br.com.smartmed.consultas.controller;

import br.com.smartmed.consultas.model.PacienteModel;
import br.com.smartmed.consultas.service.PacienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private final PacienteService service;

    public PacienteController(PacienteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PacienteModel>> listarPacientes() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteModel> getPaciente(@PathVariable Integer id) {
        return ResponseEntity.ok(service.listarPorId(id));
    }

    @PostMapping
    public ResponseEntity<PacienteModel> criarPaciente(@RequestBody PacienteModel Paciente) {
        service.adicionar(Paciente);
        return ResponseEntity.status(201).body(Paciente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteModel> atualizarPaciente(@PathVariable Integer id, @RequestBody PacienteModel novaPaciente) {
        novaPaciente.setId(id);
        service.alterar(novaPaciente);
        return ResponseEntity.ok(novaPaciente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPaciente(@PathVariable Integer id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
