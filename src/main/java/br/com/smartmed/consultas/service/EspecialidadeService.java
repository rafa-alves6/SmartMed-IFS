package br.com.smartmed.consultas.service;

import br.com.smartmed.consultas.exceptions.EspecialidadeNotFoundException;
import br.com.smartmed.consultas.model.EspecialidadeModel;
import br.com.smartmed.consultas.repository.EspecialidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EspecialidadeService {
    private final EspecialidadeRepository repo;

    public EspecialidadeService(EspecialidadeRepository repo) {
        this.repo = repo;
    }

    // GET

    public List<EspecialidadeModel> listar() {
        return repo.findAll();
    }

    // GET
    public EspecialidadeModel listarPorId(Integer id) {
        return repo.findById(id).orElseThrow(() -> new EspecialidadeNotFoundException(id));
    }

    // PUT

    public void adicionar(EspecialidadeModel especialidade) {
        repo.save(especialidade);
    }

    // PUT

    public void alterar(EspecialidadeModel especialidade) {
        repo.save(especialidade);
    }

    public void excluir(Integer id) {
        repo.deleteById(id);
    }
}

