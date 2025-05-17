package br.com.smartmed.consultas.service;

import br.com.smartmed.consultas.exceptions.MedicoNotFoundException;
import br.com.smartmed.consultas.model.MedicoModel;
import br.com.smartmed.consultas.repository.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MedicoService {
        private final MedicoRepository repo;

        public MedicoService(MedicoRepository repo) {
            this.repo = repo;
        }

        // GET

        public List<MedicoModel> listar() {
            return repo.findAll();
        }

        // GET
        public MedicoModel listarPorId(Integer id) {
            return repo.findById(id).orElseThrow(() -> new MedicoNotFoundException(id));
        }

        // PUT

        public void adicionar(MedicoModel medico) {
            repo.save(medico);
        }

        // PUT

        public void alterar(MedicoModel medico) {
            repo.save(medico);
        }

        public void excluir(Integer id) {
            repo.deleteById(id);
        }
}
