package br.com.smartmed.consultas.service;

import br.com.smartmed.consultas.exception.*;
import br.com.smartmed.consultas.model.FormaPagamentoModel;
import br.com.smartmed.consultas.repository.FormaPagamentoRepository;
import br.com.smartmed.consultas.rest.dto.FormaPagamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormaPagamentoService {
    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Transactional(readOnly = true)
    public FormaPagamentoDTO buscarPorId(Integer id) {
        FormaPagamentoModel formaPagamento = formaPagamentoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Não foi possível achar uma forma de pagamento com o ID " + id + "."));
        return formaPagamento.toDTO();
    }

    @Transactional(readOnly = true)
    public List<FormaPagamentoDTO> buscarTodos() {
        List<FormaPagamentoModel> formasPagamento = formaPagamentoRepository.findAll();
        return formasPagamento
                .stream()
                .map(formaPagamento -> formaPagamento.toDTO())
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public FormaPagamentoDTO buscarPorDescricao (String descricao) {
        FormaPagamentoModel formaPagamento = formaPagamentoRepository.findByDescricaoContainingIgnoreCase(descricao)
                .orElseThrow(() -> new ObjectNotFoundException("Não foi possível encontrar uma forma de pagamento com a descrição " + descricao + "."));
        return formaPagamento.toDTO();
    }

    @Transactional
    public FormaPagamentoDTO salvar(FormaPagamentoModel novaFormaPagamento) {
        try {
            if(formaPagamentoRepository.existsByDescricaoContainingIgnoreCase(novaFormaPagamento.getDescricao())) {
                throw new ConstraintException("A forma de pagamento " + novaFormaPagamento.getDescricao() + " já existe no banco de dados.");
            }
            return formaPagamentoRepository.save(novaFormaPagamento).toDTO();
        }

        catch(DataIntegrityException e) {
            throw new DataIntegrityException("Não foi possível salvar a forma de pagamento" + novaFormaPagamento.getDescricao());
        } catch(ConstraintException e) {
            if(e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro de restrição de integridade ao salvar a forma de pagamento" + novaFormaPagamento.getDescricao());
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Não foi possível salvar a forma de pagamento " +  novaFormaPagamento.getDescricao() + "- Violação de regra de negócio");
        } catch (SQLException e) {
            throw new SQLException("Não foi possível salvar a forma de pagamento " + novaFormaPagamento.getDescricao() + ": Ocorreu um erro no banco de dados.");
        }
    }

    public FormaPagamentoDTO atualizar(FormaPagamentoModel formaPagamentoExistente) {
        try {
            if(!formaPagamentoRepository.existsByDescricaoContainingIgnoreCase(formaPagamentoExistente.getDescricao())) {
                throw new ConstraintException("A forma de pagamento "  + formaPagamentoExistente.getDescricao() + " não existe no banco de dados." );
            }
            return formaPagamentoRepository.save(formaPagamentoExistente).toDTO();
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Não foi possível atualizar a forma de pagamento " + formaPagamentoExistente.getDescricao());
        } catch(ConstraintException e) {
            if(e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro ao atualizar a forma de pagamento " + formaPagamentoExistente.getDescricao() + ": Restrição de integridade de dados.");
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Não foi possível atualizar a forma de pagamento " + formaPagamentoExistente.getDescricao() + ": Violação de regra de negócio.");
        } catch (SQLException e) {
            throw new SQLException("Não foi possível atualizar a forma de pagamento + " + formaPagamentoExistente.getDescricao() + ": Falha na conexão com o banco de dados");
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Não foi possível atualizar a forma de pagamento " + formaPagamentoExistente.getDescricao() + "Não encontrado no banco de dados.");
        }
    }
    @Transactional
    public void deletar(FormaPagamentoModel formaPagamentoExistente) {

        try {
            //Caso ocorra uma tentativa de deletar uma forma de pagamento que não existe utilizando o id.
            if (!formaPagamentoRepository.existsById(formaPagamentoExistente.getId())) {
                throw new ConstraintException("Forma de pagamento inexistente na base de dados!");
            }

            //Deletar a forma de pagamento na base de dados.
            formaPagamentoRepository.delete(formaPagamentoExistente);

        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível deletar a forma de pagamento " + formaPagamentoExistente.getDescricao() + " !");
        } catch (ConstraintException e) {
            // Relança a mensagem original ou adiciona contexto
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro ao deletar a forma de pagamento " + formaPagamentoExistente.getDescricao() + ": Restrição de integridade de dados.");
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Erro! Não foi possível deletar a forma de pagamento " + formaPagamentoExistente.getDescricao() + ". Violação de regra de negócio!");
        } catch (SQLException e) {
            throw new SQLException("Erro! Não foi possível deletar a forma de pagamento  " + formaPagamentoExistente.getDescricao() + ". Falha na conexão com o banco de dados!");
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Erro! Não foi possível deletar a forma de pagamento " + formaPagamentoExistente.getDescricao() + ". Não encontrado no banco de dados!");
        }
    }
}


