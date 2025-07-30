package br.com.smartmed.consultas.service;

import br.com.smartmed.consultas.exception.*;
import br.com.smartmed.consultas.model.ConsultaModel;
import br.com.smartmed.consultas.model.EspecialidadeModel;
import br.com.smartmed.consultas.model.MedicoModel;
import br.com.smartmed.consultas.repository.ConsultaRepository;
import br.com.smartmed.consultas.repository.MedicoRepository;
import br.com.smartmed.consultas.rest.dto.MedicoDTO;
import br.com.smartmed.consultas.rest.dto.agendaMedica.AgendaInDTO;
import br.com.smartmed.consultas.rest.dto.agendaMedica.AgendaOutDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ConsultaRepository consultaRepository;
    @Transactional(readOnly = true)
    public AgendaOutDTO consultarAgenda(AgendaInDTO inDTO) {
        MedicoModel medico = medicoRepository.findById(inDTO.getMedicoId())
                .orElseThrow(() -> new ObjectNotFoundException("Médico com ID " + inDTO.getMedicoId() + " não encontrado."));

        if (!medico.isAtivo()) {
            throw new BusinessRuleException("Não é possível consultar a agenda de um médico inativo.");
        }

        LocalDateTime inicioDoDia = inDTO.getData().atStartOfDay();
        LocalDateTime fimDoDia = inDTO.getData().atTime(18, 0, 0);

        List<ConsultaModel> consultasDoDia = consultaRepository.findAllByMedico_IdAndDataHoraConsultaBetween(medico.getId(), inicioDoDia, fimDoDia);
        Set<LocalTime> horariosJaAgendados = consultasDoDia.stream()
                .map(consulta -> consulta.getDataHoraConsulta().toLocalTime())
                .collect(Collectors.toSet());

        List<String> horariosOcupados = new ArrayList<>();
        List<String> horariosDisponiveis = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        LocalTime horarioAtual = LocalTime.of(8, 0); // Início do expediente
        LocalTime fimDoExpediente = LocalTime.of(18, 0); // Fim do expediente

        while (horarioAtual.isBefore(fimDoExpediente)) {
            boolean isHorarioPassado = inDTO.getData().isEqual(LocalDate.now()) && horarioAtual.isBefore(LocalTime.now());

            if (!isHorarioPassado) {
                if (horariosJaAgendados.contains(horarioAtual)) {
                    horariosOcupados.add(horarioAtual.format(formatter));
                } else {
                    horariosDisponiveis.add(horarioAtual.format(formatter));
                }
            }
            horarioAtual = horarioAtual.plusMinutes(30); // Duração padrão da consulta
        }

        return new AgendaOutDTO(medico.getNome(), inDTO.getData(), horariosOcupados, horariosDisponiveis);
    }

    @Transactional(readOnly = true)
    public MedicoDTO obterPorId(Integer id) {
        MedicoModel medico = medicoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Não foi possível encontrar médico com ID " + id + "."));
        return modelMapper.map(medico, MedicoDTO.class);
    }

    @Transactional(readOnly = true)
    public MedicoDTO obterPorCrm(String crm) {
        MedicoModel medico = medicoRepository.findByCrm(crm).orElseThrow(() -> new ObjectNotFoundException("Não foi possível encontrar um médico com a CRM " + crm + "."));
        return modelMapper.map(medico, MedicoDTO.class);
    }

    @Transactional(readOnly = true)
    public List<MedicoDTO> obterPorNome(String nome) {
        List<MedicoModel> medicos = medicoRepository.findAllByNomeContainingIgnoreCase(nome);
        return medicos.stream()
                .map(medico -> modelMapper.map(medico, MedicoDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MedicoDTO> obterTodos() {
        List<MedicoModel> medicos = medicoRepository.findAll();
        return medicos.stream()
                .map(medico -> modelMapper.map(medico, MedicoDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MedicoDTO> obterTodosPorEspecialidade(EspecialidadeModel especialidade) {
        List<MedicoModel> medicos = medicoRepository.findAllByEspecialidade(especialidade);
        return medicos.stream()
                .map(medico -> modelMapper.map(medico, MedicoDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public MedicoDTO salvar(MedicoModel novoMedico) {
        try {
            if (medicoRepository.existsByCrm(novoMedico.getCrm())) {
                throw new ConstraintException("Já existe um médico cadastrado com o CRM" + novoMedico.getCrm());
            } else if (medicoRepository.existsByEmail(novoMedico.getEmail())) {
                throw new ConstraintException("Já existe um médico cadastrado com o e-mail " + novoMedico.getEmail() + ".");
            }
            return modelMapper.map(medicoRepository.save(novoMedico), MedicoDTO.class);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível salvar o médico " + novoMedico.getNome() + " !");
        } catch (ConstraintException e) {
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro de restrição de integridade ao salvar o médico " + novoMedico.getNome() + ".");
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Erro! Não foi possível salvar o médico " + novoMedico.getNome() + ". Violação de regra de negócio!");
        } catch (SQLException e) {
            throw new SQLException("Erro! Não foi possível salvar o médico " + novoMedico.getNome() + ". Falha na conexão com o banco de dados!");
        }
    }

    @Transactional
    public MedicoDTO atualizar(MedicoModel medicoExistente) {
        try {
            if (!medicoRepository.existsById(medicoExistente.getId())) {
                throw new ObjectNotFoundException("Não existe um médico com o ID " + medicoExistente.getId() + " na base de dados.");
            }
            return modelMapper.map(medicoRepository.save(medicoExistente), MedicoDTO.class);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível atualizar o médico " + medicoExistente.getNome() + " !");
        } catch (ConstraintException e) {
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro ao atualizar o médico " + medicoExistente.getNome() + ": Restrição de integridade de dados.");
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Erro! Não foi possível atualizar o médico " + medicoExistente.getNome() + ". Violação de regra de negócio!");
        } catch (SQLException e) {
            throw new SQLException("Erro! Não foi possível atualizar o médico " + medicoExistente.getNome() + ". Falha na conexão com o banco de dados!");
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Erro! Não foi possível atualizar o médico" + medicoExistente.getNome() + ". Não encontrado no banco de dados!");
        }
    }

    @Transactional
    public void deletar(MedicoModel medicoExistente) {
        try {
            if (!medicoRepository.existsById(medicoExistente.getId())) {
                throw new ObjectNotFoundException("Não existe um médico com o ID " + medicoExistente.getId() + " na base de dados.");
            }
            medicoRepository.delete(medicoExistente);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível deletar o médico " + medicoExistente.getNome() + " !");
        } catch (ConstraintException e) {
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro ao deletar o médico " + medicoExistente.getNome() + ": Restrição de integridade de dados.");
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Erro! Não foi possível deletar o médico " + medicoExistente.getNome() + ". Violação de regra de negócio!");
        } catch (SQLException e) {
            throw new SQLException("Erro! Não foi possível deletar o médico " + medicoExistente.getNome() + ". Falha na conexão com o banco de dados!");
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Erro! Não foi possível deletar o médico" + medicoExistente.getNome() + ". Não encontrado no banco de dados!");
        }
    }
}