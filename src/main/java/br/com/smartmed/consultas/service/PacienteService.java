package br.com.smartmed.consultas.service;

import br.com.smartmed.consultas.exceptions.PacienteNotFoundException;
import br.com.smartmed.consultas.model.PacienteModel;
import br.com.smartmed.consultas.repository.PacienteRepository;

import java.util.List;

public class PacienteService {
    private final PacienteRepository repo;

    public PacienteService(PacienteRepository repo) {
        this.repo = repo;
    }

    // GET

    public List<PacienteModel> listar() {
        return repo.findAll();
    }

    // GET
    public PacienteModel listarPorId(Integer id) {
        return repo.findById(id).orElseThrow(() -> new PacienteNotFoundException(id));
    }

    // PUT

    public void adicionar(PacienteModel paciente) {
        repo.save(paciente);
    }

    // PUT

    public void alterar(PacienteModel paciente) {
        repo.save(paciente);
    }

    public void excluir(Integer id) {
        repo.deleteById(id);
    }

}
