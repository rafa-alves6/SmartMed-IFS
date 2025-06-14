package br.com.smartmed.consultas.rest.controller;
import java.util.List;

import br.com.smartmed.consultas.model.FormaPagamentoModel;
import br.com.smartmed.consultas.rest.dto.FormaPagamentoDTO;
import br.com.smartmed.consultas.service.FormaPagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/forma-pagamento")
public class FormaPagamentoController {
    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @GetMapping("/id/{id}")
    public ResponseEntity<FormaPagamentoDTO> buscarPorId(@PathVariable Integer id) {
        FormaPagamentoDTO formaPagamentoDTO = formaPagamentoService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(formaPagamentoDTO);
    }

    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<FormaPagamentoDTO> buscarPorDescricao(@PathVariable String descricao) {
        FormaPagamentoDTO formaPagamentoDTO = formaPagamentoService.buscarPorDescricao(descricao);
        return ResponseEntity.status(HttpStatus.OK).body(formaPagamentoDTO);
    }

    @GetMapping()
    public ResponseEntity<List<FormaPagamentoDTO>> buscarTodos() {
        List<FormaPagamentoDTO> formaPagamentoDTO = formaPagamentoService.buscarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(formaPagamentoDTO);
    }

    @PostMapping()
    public ResponseEntity<FormaPagamentoDTO> salvar(@Valid @RequestBody FormaPagamentoModel novaFormaPagamento) {
        FormaPagamentoDTO novaFormaPagamentoDTO =  formaPagamentoService.salvar(novaFormaPagamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaFormaPagamentoDTO);
    }

    @PutMapping()
    public ResponseEntity<FormaPagamentoDTO> atualizar(@Valid @RequestBody FormaPagamentoModel formaPagamentoExistente) {
        FormaPagamentoDTO formaPagamentoExistenteDTO = formaPagamentoService.atualizar(formaPagamentoExistente);
        return ResponseEntity.status(HttpStatus.OK).body(formaPagamentoExistenteDTO);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deletar(@Valid @RequestBody FormaPagamentoModel formaPagamentoExistente) {
        formaPagamentoService.deletar(formaPagamentoExistente);
        return ResponseEntity.noContent().build();
    }

}
