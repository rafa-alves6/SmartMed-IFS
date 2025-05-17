package br.com.smartmed.consultas.service;

import br.com.smartmed.consultas.exceptions.RecepcionistaNotFoundException;
import br.com.smartmed.consultas.model.RecepcionistaModel;
import br.com.smartmed.consultas.repository.RecepcionistaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RecepcionistaService {
    private final RecepcionistaRepository repo;

    public RecepcionistaService(RecepcionistaRepository repo) {
        this.repo = repo;
    }

    // GET

    public List<RecepcionistaModel> listar() {
        return repo.findAll();
    }

    // GET
    public RecepcionistaModel listarPorId(Integer id) {
        return repo.findById(id).orElseThrow(() -> new RecepcionistaNotFoundException(id));
    }

    // PUT

    public void adicionar(RecepcionistaModel recepcionista) {
        repo.save(recepcionista);
    }

    // PUT

    public void alterar(RecepcionistaModel recepcionista) {
        repo.save(recepcionista);
    }

    public void excluir(Integer id) {
        repo.deleteById(id);
    }

}
