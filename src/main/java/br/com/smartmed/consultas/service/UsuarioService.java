package br.com.smartmed.consultas.service;

import br.com.smartmed.consultas.exception.*;
import br.com.smartmed.consultas.model.UsuarioModel;
import br.com.smartmed.consultas.repository.UsuarioRepository;
import br.com.smartmed.consultas.rest.dto.login.UsuarioInDTO;
import br.com.smartmed.consultas.rest.dto.login.UsuarioOutDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public UsuarioInDTO obterPorId(int id) {
        UsuarioModel usuario = usuarioRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("usuario com ID " + id + " não encontrado."));
        return modelMapper.map(usuario, UsuarioInDTO.class);
    }

    @Transactional(readOnly = true)
    public List<UsuarioOutDTO> obterTodos() {
        List<UsuarioModel> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioOutDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public UsuarioOutDTO salvar(UsuarioModel novousuario) {
        try {
            if (usuarioRepository.existsByEmail(novousuario.getEmail())) {
                throw new ConstraintException("Já existe um usuario com esse e-mail " + novousuario.getEmail() + " na base de dados!");
            }

            String senhaCriptografada = passwordEncoder.encode(novousuario.getSenha());
            novousuario.setSenha(senhaCriptografada);

            UsuarioModel usuarioSalvo = usuarioRepository.save(novousuario);

            return new UsuarioOutDTO("Usuário cadastrado com sucesso", usuarioSalvo.getId());

        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível salvar o usuario " + novousuario.getNome() + " !");
        } catch (ConstraintException e) {
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro de restrição de integridade ao salvar o usuario " + novousuario.getNome() + ".");
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Erro! Não foi possível salvar o usuario " + novousuario.getNome() + ". Violação de regra de negócio!");
        } catch (Exception e) { // Captura genérica para outros erros
            throw new RuntimeException("Erro inesperado ao salvar o usuário.", e);
        }
    }

    @Transactional
    public UsuarioInDTO atualizar(UsuarioModel usuarioExistente) {
        try {
            if (!usuarioRepository.existsById(usuarioExistente.getId())) {
                throw new ObjectNotFoundException("Não existe nenhum usuário com ID " + usuarioExistente.getId() + " na base de dados!");
            }
            if(usuarioExistente.getSenha() != null && !usuarioExistente.getSenha().isEmpty()){
                String senhaCriptografada = passwordEncoder.encode(usuarioExistente.getSenha());
                usuarioExistente.setSenha(senhaCriptografada);
            } else {
                UsuarioModel usuarioDoBanco = usuarioRepository.findById(usuarioExistente.getId()).get();
                usuarioExistente.setSenha(usuarioDoBanco.getSenha());
            }

            return modelMapper.map(usuarioRepository.save(usuarioExistente), UsuarioInDTO.class);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível atualizar o usuario " + usuarioExistente.getNome() + " !");
        } catch (ConstraintException e) {
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro ao atualizar o usuario " + usuarioExistente.getNome() + ": Restrição de integridade de dados.");
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Erro! Não foi possível atualizar o usuario " + usuarioExistente.getNome() + ". Violação de regra de negócio!");
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Erro! Não foi possível atualizar o usuario" + usuarioExistente.getNome() + ". Não encontrado no banco de dados!");
        }
    }

    @Transactional
    public void deletar(UsuarioModel usuarioExistente) {
        try {
            if (!usuarioRepository.existsById(usuarioExistente.getId())) {
                throw new ConstraintException("usuario inexistente na base de dados!");
            }
            usuarioRepository.delete(usuarioExistente);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível deletar o usuario " + usuarioExistente.getNome() + " !");
        } catch (ConstraintException e) {
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro ao deletar o usuario " + usuarioExistente.getNome() + ": Restrição de integridade de dados.");
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Erro! Não foi possível deletar o usuario " + usuarioExistente.getNome() + ". Violação de regra de negócio!");
        } catch (SQLException e) {
            throw new SQLException("Erro! Não foi possível atualizar o deletar " + usuarioExistente.getNome() + ". Falha na conexão com o banco de dados!");
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Erro! Não foi possível deletar o usuario" + usuarioExistente.getNome() + ". Não encontrado no banco de dados!");
        }
    }
}