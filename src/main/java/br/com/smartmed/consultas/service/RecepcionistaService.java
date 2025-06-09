package br.com.smartmed.consultas.service;

import br.com.smartmed.consultas.exception.*;
import br.com.smartmed.consultas.model.RecepcionistaModel;
import br.com.smartmed.consultas.repository.RecepcionistaRepository;
import br.com.smartmed.consultas.rest.dto.RecepcionistaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecepcionistaService {
    @Autowired
    private RecepcionistaRepository recepcionistaRepository;

    @Transactional(readOnly = true)
    public RecepcionistaDTO obterPorId(Integer id) {
        RecepcionistaModel recepcionista = recepcionistaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Não foi possível encontrar recepcionista com ID " + id + "."));
        return recepcionista.toDTO();
    }
    @Transactional(readOnly = true)
    public RecepcionistaDTO obterPorCpf(String cpf) {
        RecepcionistaModel recepcionista = recepcionistaRepository.findByCpf(cpf)
                .orElseThrow(() -> new ObjectNotFoundException("Não foi possível encontrar um recepcionista com o CPF " + cpf + "."));
        return recepcionista.toDTO();
    }
    @Transactional(readOnly = true)
    public RecepcionistaDTO obterPorNome(String nome) {
        RecepcionistaModel recepcionista = recepcionistaRepository.findByNomeContainingIgnoreCase(nome)
                .orElseThrow(() -> new ObjectNotFoundException("Não foi possível encontar um recepcionista com o nome " + nome + "."));
        return recepcionista.toDTO();
    }

    @Transactional(readOnly = true)
    public List<RecepcionistaDTO> obterTodos () {
        List<RecepcionistaModel> recepcionistas = recepcionistaRepository.findAll();
        return recepcionistas.stream()
                .map(recepcionista -> recepcionista.toDTO())
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public List<RecepcionistaDTO> obterTodosPorStatus(boolean ativo) {
        List<RecepcionistaModel> recepcionistas = recepcionistaRepository.findAllByStatus(ativo);
        return recepcionistas.stream()
                .map(recepcionista -> recepcionista.toDTO())
                .collect(Collectors.toList());
    }
    @Transactional
    public RecepcionistaDTO salvar(RecepcionistaModel novoRecepcionista) {
        try {
            if(recepcionistaRepository.existsByCpf(novoRecepcionista.getCpf())) {
                throw new ConstraintException("Já existe um recepcionista cadastrado com o CPF " + novoRecepcionista.getCpf());
            }
            return recepcionistaRepository.save(novoRecepcionista).toDTO();
        }

        catch(DataIntegrityException e) {
            throw new DataIntegrityException("Não foi possível salvar o recepcionista" + novoRecepcionista.getNome());
        } catch(ConstraintException e) {
            if(e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro de restrição de integridade ao salvar o recepcionista" + novoRecepcionista.getNome());
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Não foi possível salvar o recepcionista " +  novoRecepcionista.getNome() + "- Violação de regra de negócio");
        } catch (SQLException e) {
            throw new SQLException("Não foi possível salvar o recepcionista " + novoRecepcionista.getNome() + "Ocorreu um erro no banco de dados.");
        }
    }

    @Transactional
    public RecepcionistaDTO atualizar(RecepcionistaModel recepcionistaExistente) {
        try {
            if(!recepcionistaRepository.existsByCpf(recepcionistaExistente.getCpf())) {
                throw new ConstraintException("Não existe um recepcionista com o CPF" + recepcionistaExistente.getCpf() + "na base de dados.");
            }
            return recepcionistaRepository.save(recepcionistaExistente).toDTO();
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Não foi possível atualizar o recepcionista " + recepcionistaExistente.getNome());
        } catch(ConstraintException e) {
            if(e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro ao atualizar o recepcionista " + recepcionistaExistente.getNome() + ": Restrição de integridade de dados.");
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Não foi possível atualizar o recepcionista " + recepcionistaExistente.getNome() + ": Violação de regra de negócio.");
        } catch (SQLException e) {
            throw new SQLException("Não foi possível atualizar o recepcionista + " + recepcionistaExistente.getNome() + ": Falha na conexão com o banco de dados");
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Não foi possível atualizar o recepcionista " + recepcionistaExistente.getNome() + "Não encontrado no banco de dados.");
        }
    }
    @Transactional
    public void deletar(RecepcionistaModel recepcionistaExistente) {
        try {
            if(!recepcionistaRepository.existsByCpf(recepcionistaExistente.getCpf())) {
                throw new ConstraintException("Não existe um recepcionista com o CRM" + recepcionistaExistente.getCpf() + "na base de dados.");
            }
            recepcionistaRepository.delete(recepcionistaExistente);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Não foi possível deletar o recepcionista " + recepcionistaExistente.getNome());
        } catch(ConstraintException e) {
            if(e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro ao deletar o recepcionista " + recepcionistaExistente.getNome() + ": Restrição de integridade de dados.");
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Não foi possível deletar o recepcionista " + recepcionistaExistente.getNome() + ": Violação de regra de negócio.");
        } catch (SQLException e) {
            throw new SQLException("Não foi possível deletar o recepcionista + " + recepcionistaExistente.getNome() + ": Falha na conexão com o banco de dados");
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Não foi possível deletar o recepcionista " + recepcionistaExistente.getNome() + ": Não encontrado no banco de dados.");
        }
    }
}
