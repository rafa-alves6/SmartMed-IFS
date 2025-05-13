package br.com.smartmed.consultas.exceptions;

class ConsultaNotFoundException extends RuntimeException {
    ConsultaNotFoundException(Long id) {
        super("Não foi possível achar a consulta com o id " + id);
    }
}
