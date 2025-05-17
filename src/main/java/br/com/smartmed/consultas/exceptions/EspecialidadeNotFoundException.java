package br.com.smartmed.consultas.exceptions;

public class EspecialidadeNotFoundException extends RuntimeException {
    public EspecialidadeNotFoundException(Integer id){
        super("Não foi possível achar especialidade com o id " + id);
    }
}
