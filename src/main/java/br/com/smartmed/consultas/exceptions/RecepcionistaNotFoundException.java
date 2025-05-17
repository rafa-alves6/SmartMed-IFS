package br.com.smartmed.consultas.exceptions;

public class RecepcionistaNotFoundException extends RuntimeException {
    public RecepcionistaNotFoundException(Integer id) {
        super("Não foi possível achar recepcionista com o id " + id);
    }
}
