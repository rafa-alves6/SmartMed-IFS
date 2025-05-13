package br.com.smartmed.consultas.exceptions;

class PacienteNotFoundException extends RuntimeException {
    PacienteNotFoundException(Long id) {
        super("Não foi possível achar paciente com o id " + id);
    }
}
