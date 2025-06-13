package br.com.smartmed.consultas.rest.controller;
import java.util.List;

import br.com.smartmed.consultas.model.ConvenioModel;
import br.com.smartmed.consultas.rest.dto.ConvenioDTO;
import br.com.smartmed.consultas.service.ConvenioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/convenio")
public class ConvenioController {
    @Autowired
    private ConvenioService convenioService;

    @GetMapping("/id/{id}")
    public ResponseEntity<ConvenioDTO> obterPorId(Integer id) {
        ConvenioDTO convenioDTO = convenioService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(convenioDTO);
    }

    @GetMapping("/nome/{nome}") // Contendo
    public ResponseEntity<ConvenioDTO> obterPorNome(@PathVariable String nome) {
        ConvenioDTO convenioDTO = convenioService.buscarPorNome(nome);
        return ResponseEntity.status(HttpStatus.OK).body(convenioDTO);
    }

    @GetMapping()
    public ResponseEntity<List<ConvenioDTO>> obterTodos() {
        List<ConvenioDTO> convenio = convenioService.buscarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(convenio);
    }

    @PostMapping()
    public ResponseEntity<ConvenioDTO> salvar(ConvenioModel novoConvenio) {
        ConvenioDTO novoConvenioDTO =  convenioService.salvar(novoConvenio);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoConvenioDTO);
    }

    @PutMapping
    public ResponseEntity<ConvenioDTO> atualizar(ConvenioModel convenioExistente) {
        ConvenioDTO convenioExistenteDTO = convenioService.atualizar(convenioExistente);
        return ResponseEntity.status(HttpStatus.OK).body(convenioExistenteDTO);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletar(ConvenioModel convenioExistente) {
        convenioService.deletar(convenioExistente);
        return ResponseEntity.noContent().build();
    }
}
