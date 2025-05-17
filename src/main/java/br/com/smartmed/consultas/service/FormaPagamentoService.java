package br.com.smartmed.consultas.service;

import br.com.smartmed.consultas.exceptions.FormaPagamentoNotFoundException;
import br.com.smartmed.consultas.model.FormaPagamentoModel;
import br.com.smartmed.consultas.repository.FormaPagamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormaPagamentoService {
    private final FormaPagamentoRepository repo;

    public FormaPagamentoService(FormaPagamentoRepository repo) {
        this.repo = repo;
    }

    // GET

    public List<FormaPagamentoModel> listar() {
        return repo.findAll();
    }

    // GET
    public FormaPagamentoModel listarPorId(Integer id) {
        return repo.findById(id).orElseThrow(() -> new FormaPagamentoNotFoundException(id));
    }

    // PUT

    public void adicionar(FormaPagamentoModel formapagamento) {
        repo.save(formapagamento);
    }

    // PUT

    public void alterar(FormaPagamentoModel formapagamento) {
        repo.save(formapagamento);
    }

    public void excluir(Integer id) {
        repo.deleteById(id);
    }
}
