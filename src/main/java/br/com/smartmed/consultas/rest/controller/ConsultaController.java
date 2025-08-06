package br.com.smartmed.consultas.rest.controller;

import br.com.smartmed.consultas.model.ConsultaModel;
import br.com.smartmed.consultas.rest.dto.ConsultaDTO;
import br.com.smartmed.consultas.rest.dto.agendamento.AgendamentoAutomaticoInDTO;
import br.com.smartmed.consultas.rest.dto.agendamento.AgendamentoAutomaticoOutDTO;
import br.com.smartmed.consultas.rest.dto.cancelamento.CancelarConsultaDTO;
import br.com.smartmed.consultas.rest.dto.cancelamento.CancelarConsultaResponseDTO;
import br.com.smartmed.consultas.rest.dto.historico.HistoricoInDTO;
import br.com.smartmed.consultas.rest.dto.historico.HistoricoOutDTO;
import br.com.smartmed.consultas.rest.dto.relatorio.RelatorioInDTO;
import br.com.smartmed.consultas.rest.dto.relatorio.especialidadesFrequentes.EspecialidadeFrequenteOutDTO;
import br.com.smartmed.consultas.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/consulta")
public class ConsultaController {
    @Autowired
    private ConsultaService consultaService;

    @PostMapping("/agendar-automatico")
    public ResponseEntity<AgendamentoAutomaticoOutDTO> agendarAutomatico(@Valid @RequestBody AgendamentoAutomaticoInDTO agendamento) {
        AgendamentoAutomaticoOutDTO consultaAgendada = consultaService.agendamentoAutomatico(agendamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaAgendada);
    }
    @PostMapping("/historico")
    public ResponseEntity<List<HistoricoOutDTO>> obterHistorico(@Valid @RequestBody HistoricoInDTO inDTO) {
        List<HistoricoOutDTO> historico = consultaService.obterHistoricoConsultas(inDTO);
        return ResponseEntity.ok(historico);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<ConsultaDTO> buscarPorId(@PathVariable Long id) {
        ConsultaDTO consultaDTO = consultaService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(consultaDTO);
    }

    @GetMapping()
    public ResponseEntity<List<ConsultaDTO>> buscarTodas() {
        List<ConsultaDTO> consultas = consultaService.buscarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(consultas);
    }

    @GetMapping("/horario/{horario}")
    public ResponseEntity<List<ConsultaDTO>> buscarPorHorario(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime horarioConsulta) {
        List<ConsultaDTO> consultas = consultaService.buscarTodasPorHorario(horarioConsulta);
        return ResponseEntity.status(HttpStatus.OK).body(consultas);
    }

    @GetMapping("/convenio/nome/{nomeConvenio}")
    public ResponseEntity<List<ConsultaDTO>> buscarPorConvenioNome(@PathVariable String nomeConvenio) {
        List<ConsultaDTO> consultas = consultaService.buscarTodasPorConvenioNome(nomeConvenio);
        return ResponseEntity.status(HttpStatus.OK).body(consultas);
    }

    @GetMapping("/convenio/cnpj/{cnpjConvenio}")
    public ResponseEntity<List<ConsultaDTO>> buscarPorConvenioCnpj(@PathVariable String cnpjConvenio) {
        List<ConsultaDTO> consultas = consultaService.buscarTodasPorConvenioCNPJ(cnpjConvenio);
        return ResponseEntity.status(HttpStatus.OK).body(consultas);
    }

    @GetMapping("/recepcionista/id/{idRecepcionista}")
    public ResponseEntity<List<ConsultaDTO>> buscarPorIdRecepcionista(@PathVariable Integer idRecepcionista) {
        List<ConsultaDTO> consultas = consultaService.buscarTodasPorRecepcionistaId(idRecepcionista);
        return ResponseEntity.status(HttpStatus.OK).body(consultas);
    }

    @GetMapping("/forma-pagamento/{formaPagamentoDescricao}")
    public ResponseEntity<List<ConsultaDTO>> buscarPorFormaDePagamento(@PathVariable String formaPagamentoDescricao) {
        List<ConsultaDTO> consultas = consultaService.buscarTodasPorFormaDePagamento(formaPagamentoDescricao);
        return ResponseEntity.status(HttpStatus.OK).body(consultas);
    }

    @GetMapping("/paciente/cpf/{cpf}")
    public ResponseEntity<List<ConsultaDTO>> buscarPorPacienteCpf(@PathVariable String cpf) {
        List<ConsultaDTO> consultas = consultaService.buscarTodasPorCpfDoPaciente(cpf);
        return ResponseEntity.status(HttpStatus.OK).body(consultas);
    }

    @GetMapping("/medico/{nomeMedico}")
    public ResponseEntity<List<ConsultaDTO>> buscarPorMedicoNome(@PathVariable String nomeMedico) {
        List<ConsultaDTO> consultas = consultaService.buscarTodasPorNomeDoMedico(nomeMedico);
        return ResponseEntity.status(HttpStatus.OK).body(consultas);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ConsultaDTO>> buscarPorStatus(@PathVariable String status) {
        List<ConsultaDTO> consultas = consultaService.buscarTodosPorStatus(status);
        return ResponseEntity.status(HttpStatus.OK).body(consultas);
    }

    @PostMapping()
    public ResponseEntity<ConsultaDTO> salvar(@Valid @RequestBody ConsultaModel novaConsulta) {
        ConsultaDTO consultaDTO = consultaService.salvar(novaConsulta);
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaDTO);
    }
    @PostMapping("/especialidades-frequentes")
    public ResponseEntity<List<EspecialidadeFrequenteOutDTO>> gerarRelatorioEspecialidades(@Valid @RequestBody RelatorioInDTO inDTO) {
        List<EspecialidadeFrequenteOutDTO> relatorio = consultaService.gerarRelatorioEspecialidadesFrequentes(inDTO);
        return ResponseEntity.ok(relatorio);
    }

    @PutMapping("/cancelar")
    public ResponseEntity<CancelarConsultaResponseDTO> cancelarConsulta(@Valid @RequestBody CancelarConsultaDTO dto) {
        CancelarConsultaResponseDTO response = consultaService.cancelar(dto);
        return ResponseEntity.ok(response);
    }
    @PutMapping()
    public ResponseEntity<ConsultaDTO> atualizar(@Valid @RequestBody ConsultaModel consultaExistente) {
        ConsultaDTO consultaDTO = consultaService.atualizar(consultaExistente);
        return ResponseEntity.status(HttpStatus.OK).body(consultaDTO);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deletar(@Valid @RequestBody ConsultaModel consultaExistente) {
        consultaService.deletar(consultaExistente);
        return  ResponseEntity.noContent().build();
    }

}