package gabrielsilva.dev.exception;

import gabrielsilva.dev.dto.response.CommonErrorDTO;
import gabrielsilva.dev.dto.response.ErrorFieldDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonErrorDTO methodArgumentoNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<ErrorFieldDTO> errorFieldDTOS = fieldErrors
                .stream()
                .map(el -> new ErrorFieldDTO(el.getField(), el.getDefaultMessage()))
                .toList();

        return new CommonErrorDTO(
                "Campos inseridos são inválidos",
                HttpStatus.BAD_REQUEST.value(),
                errorFieldDTOS
        );
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public CommonErrorDTO requestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return new CommonErrorDTO(
                "Método da requisição incorreto",
                HttpStatus.METHOD_NOT_ALLOWED.value(),
                List.of()
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonErrorDTO requestMethodNotReadableException(HttpMessageNotReadableException e) {
        return new CommonErrorDTO(
                "Forma de enviar a requisição incorreta",
                HttpStatus.BAD_REQUEST.value(),
                List.of()
        );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CommonErrorDTO requestEntityNotFoundException(EntityNotFoundException e) {
        return new CommonErrorDTO(
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                List.of()
        );
    }
}
