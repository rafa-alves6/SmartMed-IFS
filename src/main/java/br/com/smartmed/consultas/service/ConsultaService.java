package br.com.smartmed.consultas.service;

import br.com.smartmed.consultas.exception.*;
import br.com.smartmed.consultas.model.ConsultaModel;
import br.com.smartmed.consultas.repository.ConsultaRepository;
import br.com.smartmed.consultas.rest.dto.ConsultaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;

    @Transactional(readOnly = true)
    public List<ConsultaDTO> buscarTodasPorHorario(LocalDateTime horario) {
        List<ConsultaModel> consultas = consultaRepository.findAllByDataHoraConsulta(horario);
        return consultas.stream().map(ConsultaModel::toDTO).collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public List<ConsultaDTO> buscarTodasPorConvenioNome(String nomeConvenio) {
        List<ConsultaModel> consultas = consultaRepository.findAllByConvenio_NomeContainingIgnoreCase(nomeConvenio);
        return consultas.stream().map(ConsultaModel::toDTO).collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public List<ConsultaDTO> buscarTodasPorConvenioCNPJ(String convenioCnpj) {
        List<ConsultaModel> consultas = consultaRepository.findAllByConvenio_Cnpj(convenioCnpj);
        return consultas.stream().map(ConsultaModel::toDTO).collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public List<ConsultaDTO> buscarTodasPorRecepcionistaId(Integer recepcionistaId) {
        List<ConsultaModel> consultas = consultaRepository.findAllByRecepcionista_Id(recepcionistaId);
        return consultas.stream().map(ConsultaModel::toDTO).collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public List<ConsultaDTO> buscarTodasPorFormaDePagamento(String formaPagamento) {
        List<ConsultaModel> consultas = consultaRepository.findAllByFormaPagamento_DescricaoContainingIgnoreCase(formaPagamento);
        return consultas.stream().map(ConsultaModel::toDTO).collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public List<ConsultaDTO> buscarTodasPorCpfDoPaciente(String pacienteCpf) {
        List<ConsultaModel> consultas = consultaRepository.findAllByPaciente_Cpf(pacienteCpf);
        return consultas.stream().map(ConsultaModel::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ConsultaDTO> buscarTodasPorNomeDoMedico(String medicoNome) {
        List<ConsultaModel> consultas = consultaRepository.findAllByMedico_NomeContainingIgnoreCase(medicoNome);
        return consultas.stream().map(ConsultaModel::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<ConsultaDTO> buscarPorIdDoMedicoEHorarioDaConsulta(Integer medicoId, LocalDateTime horarioConsulta) {
        return consultaRepository.findByMedico_IdAndDataHoraConsulta(medicoId, horarioConsulta)
                .map(ConsultaModel::toDTO);
    }

    @Transactional(readOnly = true)
    public ConsultaDTO buscarPorId(Long id) {
        ConsultaModel consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Não foi possível achar um consulta com o ID " + id + "."));
        return consulta.toDTO();
    }

    @Transactional(readOnly = true)
    public List<ConsultaDTO> buscarTodos() {
        List<ConsultaModel> consultas = consultaRepository.findAll();
        return consultas
                .stream()
                .map(ConsultaModel::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ConsultaDTO> buscarTodosPorStatus(String status) {
        List<ConsultaModel> consultas = consultaRepository.findAllByStatusContainingIgnoreCase(status);
        return consultas.stream()
                .map(ConsultaModel::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ConsultaDTO salvar(ConsultaModel novaConsulta) {
        try {
            if(consultaRepository.existsById(novaConsulta.getId())) {
                throw new ConstraintException("Já existe uma consulta com o ID" +  novaConsulta.getId() + "!");
            } else if (consultaRepository.existsByMedico_IdAndDataHoraConsulta(novaConsulta.getMedico().getId(), novaConsulta.getDataHoraConsulta())) {
                throw new ConstraintException("O médico " + novaConsulta.getMedico().getNome() + " já tem uma consulta marcada pra este horário!");
            }
            return consultaRepository.save(novaConsulta).toDTO();
        }

        catch(DataIntegrityException e) {
            throw new DataIntegrityException("Não foi possível salvar a consulta com ID " + novaConsulta.getId());
        } catch(ConstraintException e) {
            if(e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro de restrição de integridade ao salvar a consulta com ID " + novaConsulta.getId());
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Não foi possível salvar a consulta com ID " +  novaConsulta.getId() + "- Violação de regra de negócio");
        } catch (SQLException e) {
            throw new SQLException("Não foi possível salvar a consulta com ID " + novaConsulta.getId() + ": Ocorreu um erro no banco de dados.");
        }
    }

    public ConsultaDTO atualizar(ConsultaModel consultaExistente) {
        try {
            if(!consultaRepository.existsById(consultaExistente.getId())) {
                throw new ConstraintException("A consulta com ID "  + consultaExistente.getId() + " não existe no banco de dados." );
            }
            return consultaRepository.save(consultaExistente).toDTO();
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Não foi possível atualizar a consulta com ID " + consultaExistente.getId());
        } catch(ConstraintException e) {
            if(e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro ao atualizar a consulta com Id " + consultaExistente.getId() + ": Restrição de integridade de dados.");
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Não foi possível atualizar a consulta com id " + consultaExistente.getId() + ": Violação de regra de negócio.");
        } catch (SQLException e) {
            throw new SQLException("Não foi possível atualizar a consulta com id " + consultaExistente.getId() + ": Falha na conexão com o banco de dados");
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Não foi possível atualizar a consulta com ID " + consultaExistente.getId() + "Não encontrado no banco de dados.");
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
            throw new DataIntegrityException("Erro! Não foi possível deletar a consulta com ID " + consultaExistente.getId() + " !");
        } catch (ConstraintException e) {
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro ao deletar a consulta com ID " + consultaExistente.getId() + ": Restrição de integridade de dados.");
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Erro! Não foi possível deletar a consulta com ID " + consultaExistente.getId() + ". Violação de regra de negócio!");
        } catch (SQLException e) {
            throw new SQLException("Erro! Não foi possível deletar a consulta com ID " + consultaExistente.getId() + ". Falha na conexão com o banco de dados!");
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Erro! Não foi possível deletar a consulta com ID " + consultaExistente.getId() + ". Não encontrado no banco de dados!");
        }
    }
}