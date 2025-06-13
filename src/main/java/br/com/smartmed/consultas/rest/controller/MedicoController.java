package br.com.smartmed.consultas.rest.controller;
import java.util.List;

import br.com.smartmed.consultas.model.MedicoModel;
import br.com.smartmed.consultas.rest.dto.MedicoDTO;
import br.com.smartmed.consultas.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<MedicoDTO>> buscarTodosPorEspecialidade(@PathVariable String especialidade) {
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

    @GetMapping("/nome/{nome}") // Busca em uma String contendo o nome. (Ex: String 'Jo' retorna Joao, Jose, John...)
    public ResponseEntity<MedicoDTO> buscarPorNome(@PathVariable String nome) {
        MedicoDTO medico = medicoService.obterPorNome(nome);
        return ResponseEntity.status(HttpStatus.OK).body(medico);
    }

    @PostMapping()
    public ResponseEntity<MedicoDTO> salvar(@Valid @RequestBody MedicoModel novoMedico) {
        MedicoDTO novoMedicoDTO = medicoService.salvar(novoMedico);
        return ResponseEntity.status(HttpStatus.OK).body(novoMedicoDTO);
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
