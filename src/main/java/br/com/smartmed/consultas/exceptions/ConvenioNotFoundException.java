package br.com.smartmed.consultas.exceptions;

public class ConvenioNotFoundException extends RuntimeException {
    public ConvenioNotFoundException(Integer id) {
        super("Não foi possível achar convênio com o id " + id);
    }
}
