package br.com.smartmed.consultas.exceptions;

class EspecialidadeNotFoundException extends RuntimeException {
    EspecialidadeNotFoundException(Long id){
        super("Não foi possível achar especialidade com o id " + id);
    }
}
