package br.com.smartmed.consultas.service;

import br.com.smartmed.consultas.exceptions.ConsultaNotFoundException;
import br.com.smartmed.consultas.model.ConsultaModel;
import br.com.smartmed.consultas.repository.ConsultaRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {
    private final ConsultaRepository repo;

    public ConsultaService(ConsultaRepository repo) {
        this.repo = repo;
    }

    // GET

    public List<ConsultaModel> listar() {
        return repo.findAll();
    }

    // GET
    public ConsultaModel listarPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> new ConsultaNotFoundException(id));
    }

    // PUT

    public void adicionarConsulta(ConsultaModel consulta) {
        repo.save(consulta);
    }

    // PUT

    public void alterarConsulta(ConsultaModel consulta) {
        repo.save(consulta);
    }

    public void excluirConsulta(Long id) {
        repo.deleteById(id);
    }
}
