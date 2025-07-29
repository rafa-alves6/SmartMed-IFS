package br.com.smartmed.consultas.service;

import br.com.smartmed.consultas.exception.*;
import br.com.smartmed.consultas.model.EspecialidadeModel;
import br.com.smartmed.consultas.model.MedicoModel;
import br.com.smartmed.consultas.repository.MedicoRepository;
import br.com.smartmed.consultas.rest.dto.MedicoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private ModelMapper modelMapper;

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