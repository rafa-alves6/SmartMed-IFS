package br.com.smartmed.consultas.exceptions;

public class ConsultaNotFoundException extends RuntimeException {
    public ConsultaNotFoundException(Long id) {
        super("Não foi possível achar a consulta com o id " + id);
    }
}
