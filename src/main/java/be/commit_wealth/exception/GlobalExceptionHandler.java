package be.commit_wealth.exception;

import be.commit_wealth.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {

        Map<String, String> fieldErrors = new HashMap<>();

        // ดึงรายการ Violation ทั้งหมดออกมา
        ex.getBindingResult().getAllErrors().forEach(error -> {
            if (error instanceof FieldError fieldError) {
                // จะดึงชื่อ propertyNode ที่เรากำหนดไว้ใน addPropertyNode("username") มาเป็น getField() อัตโนมัติ
                fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
            } else {
                fieldErrors.put(error.getObjectName(), error.getDefaultMessage());
            }
        });

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorResponse.setMessage("Validation failed for request body");
        errorResponse.setFieldErrors(fieldErrors);
        errorResponse.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
