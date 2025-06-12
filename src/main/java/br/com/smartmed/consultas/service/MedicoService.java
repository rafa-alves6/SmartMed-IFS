package br.com.smartmed.consultas.service;

import br.com.smartmed.consultas.exception.*;
import br.com.smartmed.consultas.model.MedicoModel;
import br.com.smartmed.consultas.repository.MedicoRepository;
import br.com.smartmed.consultas.rest.dto.MedicoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicoService {
    @Autowired
    private MedicoRepository medicoRepository;

    @Transactional(readOnly = true)
    public MedicoDTO obterPorId(Integer id) {
        MedicoModel medico = medicoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Não foi possível encontrar médico com ID " + id + "."));
        return medico.toDTO();
    }
    @Transactional(readOnly = true)
    public MedicoDTO obterPorCrm(String crm) {
        MedicoModel medico = medicoRepository.findByCrm(crm)
                .orElseThrow(() -> new ObjectNotFoundException("Não foi possível encontrar um médico com a CRM " + crm + "."));
        return medico.toDTO();
    }
    @Transactional(readOnly = true)
    public MedicoDTO obterPorNome(String nome) {
        MedicoModel medico = medicoRepository.findByNomeContainingIgnoreCase(nome)
                .orElseThrow(() -> new ObjectNotFoundException("Não foi possível encontar um médico com o nome " + nome + "."));
        return medico.toDTO();
    }

    @Transactional(readOnly = true)
        public List<MedicoDTO> obterTodos () {
            List<MedicoModel> medicos = medicoRepository.findAll();
            return medicos.stream()
                    .map(medico -> medico.toDTO())
                    .collect(Collectors.toList());
        }
    @Transactional(readOnly = true)
    public List<MedicoDTO> obterTodosPorEspecialidade(String especialidadeNome) {
        List<MedicoModel> medicos = medicoRepository.findAllByEspecialidade_Nome(especialidadeNome);
        return medicos.stream()
                .map(medico -> medico.toDTO())
                .collect(Collectors.toList());
    }

    @Transactional
    public MedicoDTO salvar(MedicoModel novoMedico) {
        try {
            if(medicoRepository.existsByCrm(novoMedico.getCrm())) {
                throw new ConstraintException("Já existe um médico cadastrado com o CRM" + novoMedico.getCrm());
            } else if(medicoRepository.existsByEmail(novoMedico.getEmail())) {
                throw new ConstraintException("Já existe um médico cadastrado com o e-mail " + novoMedico.getEmail() + ".");
            }
            return medicoRepository.save(novoMedico).toDTO();
        }

        catch(DataIntegrityException e) {
            throw new DataIntegrityException("Não foi possível salvar o médico" + novoMedico.getNome());
        } catch(ConstraintException e) {
            if(e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro de restrição de integridade ao salvar o médico" + novoMedico.getNome());
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Não foi possível salvar o médico " +  novoMedico.getNome() + "- Violação de regra de negócio");
        } catch (SQLException e) {
            throw new SQLException("Não foi possível salvar o médico " + novoMedico.getNome() + "Ocorreu um erro no banco de dados.");
        }
    }

    @Transactional
    public MedicoDTO atualizar(MedicoModel medicoExistente) {
        try {
            if(!medicoRepository.existsByCrm(medicoExistente.getCrm())) {
                throw new ConstraintException("Não existe um médico com o CRM" + medicoExistente.getCrm() + "na base de dados.");
            }
            return medicoRepository.save(medicoExistente).toDTO();
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Não foi possível atualizar o médico " + medicoExistente.getNome());
        } catch(ConstraintException e) {
            if(e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro ao atualizar o médico " + medicoExistente.getNome() + ": Restrição de integridade de dados.");
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Não foi possível atualizar o médico " + medicoExistente.getNome() + ": Violação de regra de negócio.");
        } catch (SQLException e) {
            throw new SQLException("Não foi possível atualizar o médico + " + medicoExistente.getNome() + ": Falha na conexão com o banco de dados");
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Não foi possível atualizar o médico " + medicoExistente.getNome() + "Não encontrado no banco de dados.");
        }
    }
    @Transactional
    public void deletar(MedicoModel medicoExistente) {
        try {
            if(!medicoRepository.existsByCrm(medicoExistente.getCrm())) {
                throw new ConstraintException("Não existe um médico com o CRM" + medicoExistente.getCrm() + "na base de dados.");
            }
            medicoRepository.delete(medicoExistente);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Não foi possível deletar o médico " + medicoExistente.getNome());
        } catch(ConstraintException e) {
            if(e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro ao deletar o médico " + medicoExistente.getNome() + ": Restrição de integridade de dados.");
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Não foi possível deletar o médico " + medicoExistente.getNome() + ": Violação de regra de negócio.");
        } catch (SQLException e) {
            throw new SQLException("Não foi possível deletar o médico + " + medicoExistente.getNome() + ": Falha na conexão com o banco de dados");
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Não foi possível deletar o médico " + medicoExistente.getNome() + ": Não encontrado no banco de dados.");
        }
    }
}
