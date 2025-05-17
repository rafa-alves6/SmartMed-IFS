package br.com.smartmed.consultas.service;

import br.com.smartmed.consultas.exceptions.ConvenioNotFoundException;
import br.com.smartmed.consultas.model.ConvenioModel;
import br.com.smartmed.consultas.repository.ConvenioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConvenioService {
    private final ConvenioRepository repo;

    public ConvenioService(ConvenioRepository repo) {
        this.repo = repo;
    }

    // GET

    public List<ConvenioModel> listar() {
        return repo.findAll();
    }

    // GET
    public ConvenioModel listarPorId(Integer id) {
        return repo.findById(id).orElseThrow(() -> new ConvenioNotFoundException(id));
    }

    // PUT

    public void adicionar(ConvenioModel convenio) {
        repo.save(convenio);
    }

    // PUT

    public void alterar(ConvenioModel convenio) {
        repo.save(convenio);
    }

    public void excluir(Integer id) {
        repo.deleteById(id);
    }
}

