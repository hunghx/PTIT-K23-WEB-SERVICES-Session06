package ra.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ra.api.model.dto.response.DataError;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DataError> handleValidateException(MethodArgumentNotValidException ex){
        Map<String, String> details = new HashMap<>();
        ex.getFieldErrors().forEach(fieldError -> {
            details.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        DataError dataError = DataError.builder()
                .code(400)
                .message("Validation Error")
                .details(details)
                .build();
        return new ResponseEntity<>(dataError, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<DataError> handleNotFoundException(NotFoundException ex) {
        DataError dataError = DataError.builder()
                .code(404)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(dataError, HttpStatus.NOT_FOUND);
    }
}
