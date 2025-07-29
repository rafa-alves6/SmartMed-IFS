package br.com.smartmed.consultas.service;

import br.com.smartmed.consultas.exception.*;
import br.com.smartmed.consultas.model.ConvenioModel;
import br.com.smartmed.consultas.repository.ConvenioRepository;
import br.com.smartmed.consultas.rest.dto.ConvenioDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConvenioService {

    @Autowired
    private ConvenioRepository convenioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public ConvenioDTO buscarPorId(Integer id) {
        ConvenioModel convenio = convenioRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Não foi possível achar um convenio com o ID " + id + "."));
        return modelMapper.map(convenio, ConvenioDTO.class);
    }

    @Transactional(readOnly = true)
    public List<ConvenioDTO> buscarTodos() {
        List<ConvenioModel> convenios = convenioRepository.findAll();
        return convenios.stream()
                .map(convenio -> modelMapper.map(convenio, ConvenioDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ConvenioDTO buscarPorNome(String nome) {
        ConvenioModel convenio = convenioRepository.findByNomeContainingIgnoreCase(nome).orElseThrow(() -> new ObjectNotFoundException("Não foi possível encontrar um convenio com o nome " + nome + "."));
        return modelMapper.map(convenio, ConvenioDTO.class);
    }

    @Transactional(readOnly = true)
    public ConvenioDTO buscarPorCnpj(String cnpj) {
        ConvenioModel convenio = convenioRepository.findByCnpj(cnpj).orElseThrow(() -> new ObjectNotFoundException("Não foi possível encontrar um convenio com o nome " + cnpj + "."));
        return modelMapper.map(convenio, ConvenioDTO.class);
    }

    @Transactional(readOnly = true)
    public List<ConvenioDTO> buscarTodosPorStatus(boolean ativo) {
        List<ConvenioModel> convenios = convenioRepository.findAllByAtivo(ativo);
        if (convenios.isEmpty()) {
            throw new ObjectNotFoundException("Não há nenhum convênio registrado no banco de dados.");
        }
        return convenios.stream()
                .map(convenio -> modelMapper.map(convenio, ConvenioDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public ConvenioDTO salvar(ConvenioModel novoConvenio) {
        try {
            if (convenioRepository.existsByCnpj(novoConvenio.getCnpj())) {
                throw new ConstraintException("O convênio " + novoConvenio.getNome() + " já existe no banco de dados.");
            }
            return modelMapper.map(convenioRepository.save(novoConvenio), ConvenioDTO.class);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível salvar o convênio " + novoConvenio.getNome() + " !");
        } catch (ConstraintException e) {
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro de restrição de integridade ao salvar o convênio " + novoConvenio.getNome() + ".");
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Erro! Não foi possível salvar o convênio " + novoConvenio.getNome() + ". Violação de regra de negócio!");
        } catch (SQLException e) {
            throw new SQLException("Erro! Não foi possível salvar o convênio " + novoConvenio.getNome() + ". Falha na conexão com o banco de dados!");
        }
    }

    @Transactional
    public ConvenioDTO atualizar(ConvenioModel convenioExistente) {
        try {
            if (!convenioRepository.existsById(convenioExistente.getId())) {
                throw new ObjectNotFoundException("O convenio " + convenioExistente.getNome() + " não existe no banco de dados.");
            }
            return modelMapper.map(convenioRepository.save(convenioExistente), ConvenioDTO.class);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível atualizar o convênio " + convenioExistente.getNome() + " !");
        } catch (ConstraintException e) {
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro ao atualizar o convênio " + convenioExistente.getNome() + ": Restrição de integridade de dados.");
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Erro! Não foi possível atualizar o convênio " + convenioExistente.getNome() + ". Violação de regra de negócio!");
        } catch (SQLException e) {
            throw new SQLException("Erro! Não foi possível atualizar o convênio " + convenioExistente.getNome() + ". Falha na conexão com o banco de dados!");
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Erro! Não foi possível atualizar o convênio" + convenioExistente.getNome() + ". Não encontrado no banco de dados!");
        }
    }

    @Transactional
    public void deletar(ConvenioModel convenioExistente) {
        try {
            if (!convenioRepository.existsById(convenioExistente.getId())) {
                throw new ObjectNotFoundException("Convênio inexistente na base de dados!");
            }
            convenioRepository.delete(convenioExistente);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível deletar o convênio " + convenioExistente.getNome() + " !");
        } catch (ConstraintException e) {
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro ao deletar o convênio " + convenioExistente.getNome() + ": Restrição de integridade de dados.");
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Erro! Não foi possível deletar o convênio " + convenioExistente.getNome() + ". Violação de regra de negócio!");
        } catch (SQLException e) {
            throw new SQLException("Erro! Não foi possível deletar o convênio " + convenioExistente.getNome() + ". Falha na conexão com o banco de dados!");
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Erro! Não foi possível deletar o convênio" + convenioExistente.getNome() + ". Não encontrado no banco de dados!");
        }
    }
}