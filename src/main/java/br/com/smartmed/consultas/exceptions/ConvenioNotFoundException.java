package br.com.smartmed.consultas.exceptions;

class ConvenioNotFoundException extends RuntimeException {
    ConvenioNotFoundException(Long id) {
        super("Não foi possível achar convênio com o id " + id);
    }
}
