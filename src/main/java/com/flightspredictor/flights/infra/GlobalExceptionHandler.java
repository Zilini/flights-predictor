package com.flightspredictor.flights.infra;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.flightspredictor.flights.domain.error.BusinessException;

/**
 * Maneja errores de validación producidos por Bean Validation (@Valid).
 *
 * Se ejecuta automáticamente cuando un DTO no cumple las restricciones
 * definidas (por ejemplo @NotNull, @NotBlank, @Pattern, etc.).
 *
 * Retorna una respuesta HTTP 400 (Bad Request) con:
 * - Código de estado
 * - Tipo de error
 * - Detalle de los campos inválidos
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handlerValidationError(MethodArgumentNotValidException ex){

        // Mapa que contendrá los errores de validación por campo
        Map<String, String> errores = new HashMap<>();

        // Se recorren los errores de validación y se mapean.
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errores.put(error.getField(), error.getDefaultMessage())
                );
        // Construccion de la respuesta de error.
        Map<String, Object> response = new HashMap<>();
        response.put("Estado", HttpStatus.BAD_REQUEST.value());
        response.put("Error", "Error de Validacion");
        response.put("Detalles", errores);

        //Retorna un HTTP 400
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    // Maneja las excepciones de tipo BusinessException lanzadas en la aplicación
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handlerBusinessError(BusinessException ex){
        //Mapa que contendrá los errores de validación por campo
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("Estado", HttpStatus.BAD_REQUEST.value());
        respuesta.put("Codigo", ex.getCode());
        respuesta.put("Mensaje", ex.getMessage());

        //Retorna un HTTP 400
        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }
    
    /**
     * Maneja excepciones no controladas o inesperadas.
     * Su objetivo es:
     * - Evitar que el stacktrace llegue al cliente
     * - Retornar una respuesta HTTP controlada y segura
     * - Notificar al cliente que ocurrió un error interno
     *
     * Retorna:
     * - HTTP 500 (Internal Server Error)
     * - Un mensaje genérico sin exponer detalles técnico
     */
    @ExceptionHandler(Exception.class)
    // Construccion de la respuesta de error.
    public ResponseEntity<Object> handlerUnexpected(Exception ex){
        return new ResponseEntity<>(
                Map.of(
                        "Estado" , 500,
                        "Error", "INTERNAL ERROR",
                        "Mensaje", "Error inesperado"
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

}
