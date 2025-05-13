package br.com.smartmed.consultas.exceptions;

class RecepcionistaNotFoundException extends RuntimeException {
    RecepcionistaNotFoundException(Long id) {
        super("Não foi possível achar recepcionista com o id " + id);
    }
}
