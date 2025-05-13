package br.com.smartmed.consultas.exceptions;

class MedicoNotFoundException extends RuntimeException {
    MedicoNotFoundException(Long id) {
        super("Não foi possível achar médico com id " + id);
    }
}
