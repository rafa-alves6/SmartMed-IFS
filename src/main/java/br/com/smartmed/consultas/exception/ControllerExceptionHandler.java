package br.com.smartmed.consultas.exception;


import br.com.smartmed.consultas.rest.dto.login.Cargos;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Não encontrado", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Integridade de dados", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(ConstraintException.class)
    public ResponseEntity<StandardError> constraint(ConstraintException e, HttpServletRequest request) {
        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Restrição de dados", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<StandardError> businessRule(BusinessRuleException e, HttpServletRequest request) {
        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.CONFLICT.value(), "Regra de negócio", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<StandardError> sql(SQLException e, HttpServletRequest request){
        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro de conexão com o banco de dados", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }

    @ExceptionHandler(CpfCnpjException.class)
    public ResponseEntity<StandardError> sql(CpfCnpjException e, HttpServletRequest request){
        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro de inserção de dados do CPF/CNPJ", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpServletRequest request) {
        Throwable cause = ex.getCause();
        if (cause instanceof InvalidFormatException invalidFormatException) {
            // Verifica se o erro é de um valor inválido para o enum 'Cargos'
            if (invalidFormatException.getTargetType() != null && invalidFormatException.getTargetType().isEnum() && invalidFormatException.getTargetType().equals(Cargos.class)) {
                String validValues = Arrays.stream(Cargos.values())
                        .map(Enum::name)
                        .collect(Collectors.joining(", "));
                String message = String.format("Valor inválido para o campo 'perfil'. Os valores aceitos são: %s.", validValues);
                StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro de Formato", message, request.getRequestURI());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
            }

        // Verifica se a causa raiz é um erro de formatação de data
            if (invalidFormatException.getCause() instanceof DateTimeParseException) {
                // Retorna uma mensagem clara para o cliente sobre o erro de formatação de data
                StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),"Erro de formatação de dados","Formato de data inválido. Use o padrão 'yyyy-MM-dd'.", request.getRequestURI());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
            }
        }

        // Mensagem genérica para outros casos
        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),"Erro de leitura JSON","Erro de leitura na requisição. Verifique o formato dos dados enviados.", request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        ValidationError err = new ValidationError();
        err.setTimestamp(System.currentTimeMillis());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setError("Erro de validação");
        err.setMessage("Um ou mais campos estão inválidos.");
        err.setPath(request.getRequestURI());

        // Captura os erros específicos de campo
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            err.addError(error.getField(), error.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}