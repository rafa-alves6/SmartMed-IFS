package br.com.smartmed.consultas.rest.controller;

import br.com.smartmed.consultas.model.RecepcionistaModel;
import br.com.smartmed.consultas.rest.dto.RecepcionistaDTO;
import br.com.smartmed.consultas.service.RecepcionistaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recepcionista")
public class RecepcionistaController {
    @Autowired
    private RecepcionistaService recepcionistaService;

    @GetMapping("/id/{id}")
    public ResponseEntity<RecepcionistaDTO> obterPorId(@PathVariable Integer id) {
        RecepcionistaDTO recepcionista = recepcionistaService.obterPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(recepcionista);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<RecepcionistaDTO>> obterTodosPorNome(@PathVariable String nome) {
        List<RecepcionistaDTO> recepcionista = recepcionistaService.obterTodosPorNome(nome);
        return ResponseEntity.status(HttpStatus.OK).body(recepcionista);
    }

    @GetMapping()
    public ResponseEntity<List<RecepcionistaDTO>> obterTodos() {
        List<RecepcionistaDTO> recepcionistas = recepcionistaService.obterTodos();
        return ResponseEntity.status(HttpStatus.OK).body(recepcionistas);
    }

    @PostMapping
    public ResponseEntity<RecepcionistaDTO> salvar(@Valid @RequestBody RecepcionistaModel novoRecepcionista) {
        RecepcionistaDTO novoRecepcionistaDTO = recepcionistaService.salvar(novoRecepcionista);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoRecepcionistaDTO);
    }

    @PutMapping
    public ResponseEntity<RecepcionistaDTO> atualizar(@Valid @RequestBody RecepcionistaModel recepcionistaExistente) {
        RecepcionistaDTO recepcionistaExistenteDTO = recepcionistaService.atualizar(recepcionistaExistente);
        return ResponseEntity.status(HttpStatus.OK).body(recepcionistaExistenteDTO);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletar(@Valid @RequestBody RecepcionistaModel recepcionistaExistente) {
        recepcionistaService.deletar(recepcionistaExistente);
        return ResponseEntity.noContent().build();
    }
}