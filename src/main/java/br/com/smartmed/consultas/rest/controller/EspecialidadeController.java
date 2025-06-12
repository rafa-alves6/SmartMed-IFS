package br.com.smartmed.consultas.rest.controller;

import br.com.smartmed.consultas.model.EspecialidadeModel;
import br.com.smartmed.consultas.rest.dto.EspecialidadeDTO;
import br.com.smartmed.consultas.service.EspecialidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/especialidade")
public class EspecialidadeController {
    @Autowired
    private EspecialidadeService especialidadeService;

    @GetMapping("/id/{id}")
    public ResponseEntity<EspecialidadeDTO> obterPorId(@PathVariable Integer id) {
        EspecialidadeDTO especialidadeDTO = especialidadeService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(especialidadeDTO);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<EspecialidadeDTO> obterPorNome(@PathVariable String nome) {
        EspecialidadeDTO especialidadeDTO = especialidadeService.buscarPorNome(nome);
        return ResponseEntity.status(HttpStatus.OK).body(especialidadeDTO);
    }

    @GetMapping()
    public ResponseEntity<List<EspecialidadeDTO>> obterTodos() {
        List<EspecialidadeDTO> especialidades = especialidadeService.buscarTodos();
        return ResponseEntity.ok(especialidades);
    }

    @PostMapping()
    public ResponseEntity<EspecialidadeDTO> salvar(@Valid @RequestBody EspecialidadeModel novaEspecialidade) {
        EspecialidadeDTO novaEspecialidadeDTO = especialidadeService.salvar(novaEspecialidade);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaEspecialidadeDTO);
    }

    @PutMapping()
    public ResponseEntity<EspecialidadeDTO> atualizar(@Valid @RequestBody EspecialidadeModel especialidadeExistente) {
        EspecialidadeDTO especialidadeExistenteDTO = especialidadeService.atualizar(especialidadeExistente);
        return ResponseEntity.status(HttpStatus.OK).body(especialidadeExistenteDTO);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deletar(@Valid @RequestBody EspecialidadeModel especialidadeExistente) {
        especialidadeService.deletar(especialidadeExistente);
        return ResponseEntity.noContent().build();
    }
