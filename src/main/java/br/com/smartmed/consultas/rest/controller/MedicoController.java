package br.com.smartmed.consultas.rest.controller;

import br.com.smartmed.consultas.model.EspecialidadeModel;
import br.com.smartmed.consultas.model.MedicoModel;
import br.com.smartmed.consultas.rest.dto.MedicoDTO;
import br.com.smartmed.consultas.rest.dto.agendaMedica.AgendaInDTO;
import br.com.smartmed.consultas.rest.dto.agendaMedica.AgendaOutDTO;
import br.com.smartmed.consultas.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medico")
public class MedicoController {
    @Autowired
    private MedicoService medicoService;
    @GetMapping("/id/{id}")
    public ResponseEntity<MedicoDTO> buscarPorId(@PathVariable Integer id) {
        MedicoDTO medico = medicoService.obterPorId(id);
        return  ResponseEntity.status(HttpStatus.OK).body(medico);
    }

    @GetMapping("/especialidade/{especialidade}")
    public ResponseEntity<List<MedicoDTO>> buscarTodosPorEspecialidade(@Valid @RequestBody EspecialidadeModel especialidade) {
        List<MedicoDTO> medicos = medicoService.obterTodosPorEspecialidade(especialidade);
        return ResponseEntity.status(HttpStatus.OK).body(medicos);
    }

    @GetMapping()
    public ResponseEntity<List<MedicoDTO>> buscarTodos() {
        List<MedicoDTO> medicos = medicoService.obterTodos();
        return ResponseEntity.status(HttpStatus.OK).body(medicos);
    }

    @GetMapping("/crm/{crm}")
    public ResponseEntity<MedicoDTO> buscarPorCrm(@PathVariable String crm) {
        MedicoDTO medico = medicoService.obterPorCrm(crm);
        return ResponseEntity.status(HttpStatus.OK).body(medico);
    }

    @GetMapping("/nome") // Retorna uma lista de médicos baseado na String. (Ex: String "Jo" retorna medicos com nome "João", "José", "John"...)
    public ResponseEntity<List<MedicoDTO>> buscarPorNomeContendo(@RequestParam String nome) {
        List<MedicoDTO> medicos = medicoService.obterPorNome(nome);
        return ResponseEntity.status(HttpStatus.OK).body(medicos);
    }

    @PostMapping("/agenda")
    public ResponseEntity<AgendaOutDTO> consultarAgenda(@Valid @RequestBody AgendaInDTO request) {
        AgendaOutDTO agenda = medicoService.consultarAgenda(request);
        return ResponseEntity.ok(agenda);
    }

    @PostMapping()
    public ResponseEntity<MedicoDTO> salvar(@Valid @RequestBody MedicoModel novoMedico) {
        MedicoDTO novoMedicoDTO = medicoService.salvar(novoMedico);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoMedicoDTO);
    }

    @PutMapping()
    public ResponseEntity<MedicoDTO> atualizar(@Valid @RequestBody MedicoModel medicoExistente) {
        MedicoDTO medicoExistenteDTO = medicoService.atualizar(medicoExistente);
        return ResponseEntity.status(HttpStatus.OK).body(medicoExistenteDTO);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deletar(@Valid @RequestBody MedicoModel medicoExistente) {
        medicoService.deletar(medicoExistente);
        return ResponseEntity.noContent().build();
    }

}