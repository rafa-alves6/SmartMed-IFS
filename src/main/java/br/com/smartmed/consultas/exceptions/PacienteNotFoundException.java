package br.com.smartmed.consultas.exceptions;

public class PacienteNotFoundException extends RuntimeException {
    public PacienteNotFoundException(Integer id) {
        super("Não foi possível achar paciente com o id " + id);
    }
}
