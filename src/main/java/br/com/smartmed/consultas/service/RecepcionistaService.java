package br.com.smartmed.consultas.service;

import br.com.smartmed.consultas.exception.*;
import br.com.smartmed.consultas.model.RecepcionistaModel;
import br.com.smartmed.consultas.repository.RecepcionistaRepository;
import br.com.smartmed.consultas.rest.dto.RecepcionistaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecepcionistaService {

    @Autowired
    private RecepcionistaRepository recepcionistaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public RecepcionistaDTO obterPorId(Integer id) {
        RecepcionistaModel recepcionista = recepcionistaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Não foi possível encontrar recepcionista com ID " + id + "."));
        return modelMapper.map(recepcionista, RecepcionistaDTO.class);
    }

    @Transactional(readOnly = true)
    public RecepcionistaDTO obterPorCpf(String cpf) {
        RecepcionistaModel recepcionista = recepcionistaRepository.findByCpf(cpf).orElseThrow(() -> new ObjectNotFoundException("Não foi possível encontrar um recepcionista com o CPF " + cpf + "."));
        return modelMapper.map(recepcionista, RecepcionistaDTO.class);
    }

    @Transactional(readOnly = true)
    public List<RecepcionistaDTO> obterTodosPorNome(String nome) {
        List<RecepcionistaModel> recepcionistas = recepcionistaRepository.findAllByNomeContainingIgnoreCase(nome);
        return recepcionistas.stream()
                .map(recepcionista -> modelMapper.map(recepcionista, RecepcionistaDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<RecepcionistaDTO> obterTodos() {
        List<RecepcionistaModel> recepcionistas = recepcionistaRepository.findAll();
        return recepcionistas.stream()
                .map(recepcionista -> modelMapper.map(recepcionista, RecepcionistaDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<RecepcionistaDTO> obterTodosPorStatus(boolean ativo) {
        List<RecepcionistaModel> recepcionistas = recepcionistaRepository.findAllByStatus(ativo);
        return recepcionistas.stream()
                .map(recepcionista -> modelMapper.map(recepcionista, RecepcionistaDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public RecepcionistaDTO salvar(RecepcionistaModel novoRecepcionista) {
        try {
            if (recepcionistaRepository.existsByCpf(novoRecepcionista.getCpf())) {
                throw new ConstraintException("Já existe um recepcionista cadastrado com o CPF " + novoRecepcionista.getCpf());
            }
            return modelMapper.map(recepcionistaRepository.save(novoRecepcionista), RecepcionistaDTO.class);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível salvar o(a) recepcionista " + novoRecepcionista.getNome() + " !");
        } catch (ConstraintException e) {
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro de restrição de integridade ao salvar o(a) recepcionista " + novoRecepcionista.getNome() + ".");
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Erro! Não foi possível salvar o(a) recepcionista " + novoRecepcionista.getNome() + ". Violação de regra de negócio!");
        } catch (SQLException e) {
            throw new SQLException("Erro! Não foi possível salvar o(a) recepcionista " + novoRecepcionista.getNome() + ". Falha na conexão com o banco de dados!");
        }
    }

    @Transactional
    public RecepcionistaDTO atualizar(RecepcionistaModel recepcionistaExistente) {
        try {
            if (!recepcionistaRepository.existsById(recepcionistaExistente.getId())) {
                throw new ObjectNotFoundException("Não existe um recepcionista com o ID " + recepcionistaExistente.getId() + " na base de dados.");
            }
            return modelMapper.map(recepcionistaRepository.save(recepcionistaExistente), RecepcionistaDTO.class);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível atualizar o(a) recepcionista " + recepcionistaExistente.getNome() + " !");
        } catch (ConstraintException e) {
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro ao atualizar o(a) recepcionista " + recepcionistaExistente.getNome() + ": Restrição de integridade de dados.");
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Erro! Não foi possível atualizar o(a) recepcionista " + recepcionistaExistente.getNome() + ". Violação de regra de negócio!");
        } catch (SQLException e) {
            throw new SQLException("Erro! Não foi possível atualizar o(a) recepcionista " + recepcionistaExistente.getNome() + ". Falha na conexão com o banco de dados!");
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Erro! Não foi possível atualizar o(a) recepcionista " + recepcionistaExistente.getNome() + ". Não encontrado no banco de dados!");
        }
    }

    @Transactional
    public void deletar(RecepcionistaModel recepcionistaExistente) {
        try {
            if (!recepcionistaRepository.existsById(recepcionistaExistente.getId())) {
                throw new ObjectNotFoundException("Não existe um recepcionista com o ID " + recepcionistaExistente.getId() + " na base de dados.");
            }
            recepcionistaRepository.delete(recepcionistaExistente);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível deletar o(a) recepcionista " + recepcionistaExistente.getNome() + " !");
        } catch (ConstraintException e) {
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro ao deletar o(a) recepcionista " + recepcionistaExistente.getNome() + ": Restrição de integridade de dados.");
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Erro! Não foi possível deletar o(a) recepcionista " + recepcionistaExistente.getNome() + ". Violação de regra de negócio!");
        } catch (SQLException e) {
            throw new SQLException("Erro! Não foi possível deletar o(a) recepcionista " + recepcionistaExistente.getNome() + ". Falha na conexão com o banco de dados!");
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Erro! Não foi possível deletar o(a) recepcionista " + recepcionistaExistente.getNome() + ". Não encontrado no banco de dados!");
        }
    }
}