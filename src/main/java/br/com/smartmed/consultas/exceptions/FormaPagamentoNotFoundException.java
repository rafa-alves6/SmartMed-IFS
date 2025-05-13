package br.com.smartmed.consultas.exceptions;

class FormaPagamentoNotFoundException extends RuntimeException {
    FormaPagamentoNotFoundException(Long id) {
        super("Não foi possível achar a forma de pagamento com o id " + id);
    }
}
