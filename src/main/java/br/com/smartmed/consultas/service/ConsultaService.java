package br.com.smartmed.consultas.service;

import br.com.smartmed.consultas.exception.*;
import br.com.smartmed.consultas.model.ConsultaModel;
import br.com.smartmed.consultas.repository.ConsultaRepository;
import br.com.smartmed.consultas.rest.dto.ConsultaDTO;
import br.com.smartmed.consultas.rest.dto.FaturamentoPorConvenioDTO;
import br.com.smartmed.consultas.rest.dto.FaturamentoPorFormaPagamentoDTO;
import br.com.smartmed.consultas.rest.dto.RelatorioOutDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<ConsultaDTO> buscarTodasPorHorario(LocalDateTime horario) {
        if (horario == null) {
            throw new BusinessRuleException("Informe o horário que deseja buscar.");
        }
        List<ConsultaModel> consultas = consultaRepository.findAllByDataHoraConsulta(horario);
        return consultas.stream()
                .map(consulta -> modelMapper.map(consulta, ConsultaDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ConsultaDTO> buscarTodasPorConvenioNome(String nomeConvenio) {
        List<ConsultaModel> consultas = consultaRepository.findAllByConvenio_NomeContainingIgnoreCase(nomeConvenio);
        return consultas.stream()
                .map(consulta -> modelMapper.map(consulta, ConsultaDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ConsultaDTO> buscarTodasPorConvenioCNPJ(String convenioCnpj) {
        List<ConsultaModel> consultas = consultaRepository.findAllByConvenio_Cnpj(convenioCnpj);
        return consultas.stream()
                .map(consulta -> modelMapper.map(consulta, ConsultaDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ConsultaDTO> buscarTodasPorRecepcionistaId(Integer recepcionistaId) {
        List<ConsultaModel> consultas = consultaRepository.findAllByRecepcionista_Id(recepcionistaId);
        return consultas.stream()
                .map(consulta -> modelMapper.map(consulta, ConsultaDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ConsultaDTO> buscarTodasPorFormaDePagamento(String formaPagamento) {
        List<ConsultaModel> consultas = consultaRepository.findAllByFormaPagamento_DescricaoContainingIgnoreCase(formaPagamento);
        return consultas.stream()
                .map(consulta -> modelMapper.map(consulta, ConsultaDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ConsultaDTO> buscarTodasPorCpfDoPaciente(String pacienteCpf) {
        List<ConsultaModel> consultas = consultaRepository.findAllByPaciente_Cpf(pacienteCpf);
        return consultas.stream()
                .map(consulta -> modelMapper.map(consulta, ConsultaDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ConsultaDTO> buscarTodasPorNomeDoMedico(String medicoNome) {
        List<ConsultaModel> consultas = consultaRepository.findAllByMedico_NomeContainingIgnoreCase(medicoNome);
        return consultas.stream()
                .map(consulta -> modelMapper.map(consulta, ConsultaDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<ConsultaDTO> buscarPorIdDoMedicoEHorarioDaConsulta(Integer medicoId, LocalDateTime horarioConsulta) {
        return consultaRepository.findByMedico_IdAndDataHoraConsulta(medicoId, horarioConsulta)
                .map(consulta -> modelMapper.map(consulta, ConsultaDTO.class));
    }

    @Transactional(readOnly = true)
    public ConsultaDTO buscarPorId(Long id) {
        ConsultaModel consulta = consultaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Não foi possível achar um consulta com o ID " + id + "."));
        return modelMapper.map(consulta, ConsultaDTO.class);
    }

    @Transactional(readOnly = true)
    public List<ConsultaDTO> buscarTodos() {
        List<ConsultaModel> consultas = consultaRepository.findAll();
        return consultas.stream()
                .map(consulta -> modelMapper.map(consulta, ConsultaDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ConsultaDTO> buscarTodosPorStatus(String status) {
        List<ConsultaModel> consultas = consultaRepository.findAllByStatusContainingIgnoreCase(status);
        return consultas.stream()
                .map(consulta -> modelMapper.map(consulta, ConsultaDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public ConsultaDTO salvar(ConsultaModel novaConsulta) {
        try {
            ConsultaModel consultaSalva = consultaRepository.save(novaConsulta);
            ConsultaModel consultaCompleta = consultaRepository.findById(consultaSalva.getId())
                    .orElseThrow(() -> new ObjectNotFoundException("Erro ao buscar consulta salva com ID " + consultaSalva.getId()));
            return modelMapper.map(consultaCompleta, ConsultaDTO.class);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível salvar a consulta!");
        } catch (ConstraintException e) {
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro de restrição de integridade ao salvar a consulta.");
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Erro! Não foi possível salvar a consulta. Violação de regra de negócio!");
        } catch (SQLException e) {
            throw new SQLException("Erro! Não foi possível salvar a consulta. Falha na conexão com o banco de dados!");
        }
    }

    @Transactional
    public ConsultaDTO atualizar(ConsultaModel consultaExistente) {
        try {
            if (!consultaRepository.existsById(consultaExistente.getId())) {
                throw new ObjectNotFoundException("A consulta com ID " + consultaExistente.getId() + " não existe no banco de dados.");
            }
            return modelMapper.map(consultaRepository.save(consultaExistente), ConsultaDTO.class);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível atualizar a consulta!");
        } catch (ConstraintException e) {
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro ao atualizar a consulta: Restrição de integridade de dados.");
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Erro! Não foi possível atualizar a consulta. Violação de regra de negócio!");
        } catch (SQLException e) {
            throw new SQLException("Erro! Não foi possível atualizar a consulta. Falha na conexão com o banco de dados!");
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Erro! Não foi possível atualizar a consulta. Não encontrada no banco de dados!");
        }
    }

    @Transactional
    public void deletar(ConsultaModel consultaExistente) {
        try {
            if (!consultaRepository.existsById(consultaExistente.getId())) {
                throw new ConstraintException("Consulta inexistente na base de dados!");
            }
            consultaRepository.delete(consultaExistente);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível deletar a consulta!");
        } catch (ConstraintException e) {
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro ao deletar a consulta: Restrição de integridade de dados.");
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Erro! Não foi possível deletar a consulta. Violação de regra de negócio!");
        } catch (SQLException e) {
            throw new SQLException("Erro! Não foi possível deletar a consulta. Falha na conexão com o banco de dados!");
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Erro! Não foi possível deletar a consulta. Não encontrada no banco de dados!");
        }
    }

    @Transactional(readOnly = true)
    public RelatorioOutDTO gerarRelatorioFaturamento(LocalDate dataInicio, LocalDate dataFim) {
        if (dataInicio == null || dataFim == null) {
            throw new BusinessRuleException("Data inicio e data fim são obrigatórias");
        }
        if (dataInicio.isAfter(dataFim)) {
            throw new BusinessRuleException("Data de inicio não pode ser posterior a data fim");
        }
        double totalGeral = consultaRepository.calcularTotalGeralPorPeriodo(dataInicio, dataFim);
        List<FaturamentoPorFormaPagamentoDTO> faturamentoPorFormaPagamento = consultaRepository.buscarFaturamentoPorFormaPagamento(dataInicio, dataFim);
        List<FaturamentoPorConvenioDTO> faturamentoPorConvenio = consultaRepository.buscarFaturamentoPorConvenio(dataInicio, dataFim);
        return new RelatorioOutDTO(totalGeral, faturamentoPorFormaPagamento, faturamentoPorConvenio);
    }
}