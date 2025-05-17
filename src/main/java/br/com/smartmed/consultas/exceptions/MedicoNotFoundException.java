package br.com.smartmed.consultas.exceptions;

public class MedicoNotFoundException extends RuntimeException {
    public MedicoNotFoundException(Integer id) {
        super("Não foi possível achar médico com id " + id);
    }
}
