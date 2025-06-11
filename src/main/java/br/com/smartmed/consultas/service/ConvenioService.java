package br.com.smartmed.consultas.service;

import br.com.smartmed.consultas.exception.*;
import br.com.smartmed.consultas.model.ConvenioModel;
import br.com.smartmed.consultas.repository.ConvenioRepository;
import br.com.smartmed.consultas.rest.dto.ConvenioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConvenioService {
    @Autowired
    private ConvenioRepository convenioRepository;

    @Transactional(readOnly = true)
    public ConvenioDTO buscarPorId(Integer id) {
        ConvenioModel convenio = convenioRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Não foi possível achar um convenio com o ID " + id + "."));
        return convenio.toDTO();
    }
    @Transactional(readOnly = true)
    public List<ConvenioDTO> buscarTodos() {
        List<ConvenioModel> convenios = convenioRepository.findAll();
        return convenios
                .stream()
                .map(convenio -> convenio.toDTO())
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public ConvenioDTO buscarPorNome (String nome) {
        ConvenioModel convenio = convenioRepository.findByNomeContainingIgnoreCase(nome)
                .orElseThrow(() -> new ObjectNotFoundException("Não foi possível encontrar um convenio com o nome " + nome + "."));
        return convenio.toDTO();
    }
    @Transactional(readOnly = true)
    public ConvenioDTO buscarPorCnpj (String cnpj) {
        ConvenioModel convenio = convenioRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new ObjectNotFoundException("Não foi possível encontrar um convenio com o nome " + cnpj + "."));
        return convenio.toDTO();
    }
    @Transactional(readOnly = true)
    public List<ConvenioDTO> buscarTodosPorStatus(boolean ativo) { // true = ativo, false = inativo
       List<ConvenioModel> convenios = convenioRepository.findAllByStatus(ativo);
        if(convenios.isEmpty()){
            throw new ObjectNotFoundException("Não há nenhum convênio registrado no banco de dados.");
        }
       return convenios.stream()
               .map(convenio -> convenio.toDTO())
               .collect(Collectors.toList());
    }
    @Transactional
    public ConvenioDTO salvar(ConvenioModel novoConvenio) {
        try {
            if(convenioRepository.existsByCnpj(novoConvenio.getCnpj())) {
                throw new ConstraintException("O convênio " + novoConvenio.getNome() + " já existe no banco de dados.");
            }
            return convenioRepository.save(novoConvenio).toDTO();
        }

        catch(DataIntegrityException e) {
            throw new DataIntegrityException("Não foi possível salvar o convenio" + novoConvenio.getNome());
        } catch(ConstraintException e) {
            if(e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro de restrição de integridade ao salvar o convenio" + novoConvenio.getNome());
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Não foi possível salvar o convenio " +  novoConvenio.getNome() + "- Violação de regra de negócio");
        } catch (SQLException e) {
            throw new SQLException("Não foi possível salvar o convênio " + novoConvenio.getNome() + ": Ocorreu um erro no banco de dados.");
        }
    }

    public ConvenioDTO atualizar(ConvenioModel convenioExistente) {
        try {
            if(!convenioRepository.existsByCnpj(convenioExistente.getCnpj())) {
                throw new ConstraintException("O convenio "  + convenioExistente.getNome() + " não existe no banco de dados." );
            }
            return convenioRepository.save(convenioExistente).toDTO();
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Não foi possível atualizar o convênio " + convenioExistente.getNome());
        } catch(ConstraintException e) {
            if(e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro ao atualizar o convênio " + convenioExistente.getNome() + ": Restrição de integridade de dados.");
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Não foi possível atualizar o convênio " + convenioExistente.getNome() + ": Violação de regra de negócio.");
        } catch (SQLException e) {
            throw new SQLException("Não foi possível atualizar o convênio + " + convenioExistente.getNome() + ": Falha na conexão com o banco de dados");
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Não foi possível atualizar o convênio " + convenioExistente.getNome() + "Não encontrado no banco de dados.");
        }
    }
    @Transactional
    public void deletar(ConvenioModel convenioExistente) {

        try {
            //Caso ocorra uma tentativa de deletar um convenio que não existe utilizando o id.
            if (!convenioRepository.existsById(convenioExistente.getId())) {
                throw new ConstraintException("Forma de pagamento inexistente na base de dados!");
            }

            //Deletar o convênio na base de dados.
            convenioRepository.delete(convenioExistente);

        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível deletar o convênio " + convenioExistente.getNome() + " !");
        } catch (ConstraintException e) {
            // Relança a mensagem original ou adiciona contexto
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro ao deletar o convênio " + convenioExistente.getNome() + ": Restrição de integridade de dados.");
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Erro! Não foi possível deletar o convênio " + convenioExistente.getNome() + ". Violação de regra de negócio!");
        } catch (SQLException e) {
            throw new SQLException("Erro! Não foi possível deletar o convênio " + convenioExistente.getNome() + ". Falha na conexão com o banco de dados!");
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Erro! Não foi possível deletar o convênio " + convenioExistente.getNome() + ". Não encontrado no banco de dados!");
        }
    }
}
