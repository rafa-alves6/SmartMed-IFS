package br.com.smartmed.consultas.exceptions;

public class FormaPagamentoNotFoundException extends RuntimeException {
    public FormaPagamentoNotFoundException(Integer id) {
        super("Não foi possível achar a forma de pagamento com o id " + id);
    }
}
