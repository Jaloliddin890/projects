package uz.pdp.SpringBootDemoApplication.Handler;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.pdp.SpringBootDemoApplication.DTO.ErrorDTO;
import uz.pdp.SpringBootDemoApplication.Exceptions.NotFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> error(NotFoundException ex, HttpServletRequest request){
        return ResponseEntity.status(404)
                .body(Map.of(
                        "error_message", ex.getMessage(),
                        "error_code", 404,
                        "error_path", request.getRequestURI(),
                        "error_time", LocalDateTime.now()

                ));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> notValid(MethodArgumentNotValidException ex, HttpServletRequest request){

        Map<String, String> errorBody = new HashMap<>();
        for (FieldError fieldError : ex.getFieldErrors()) {
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            errorBody.put(field, message);
        }

        return ResponseEntity.status(404)
                .body(ErrorDTO.builder()
                .errorPath(request.getRequestURI())
                        .errorCode(String.valueOf(404))
                        .errorBody(errorBody.toString())
                        .build());
    }
}
