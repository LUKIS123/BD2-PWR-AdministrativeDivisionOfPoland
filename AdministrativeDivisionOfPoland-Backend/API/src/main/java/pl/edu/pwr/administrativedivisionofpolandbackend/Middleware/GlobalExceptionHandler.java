package pl.edu.pwr.administrativedivisionofpolandbackend.Middleware;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.edu.pwr.administrativedivisionofpolandbackend.Exceptions.InvalidReportRequestException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({InvalidReportRequestException.class})
    public ResponseEntity<Object> handleStudentNotFoundException(InvalidReportRequestException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Object> handleStudentNotFoundException(EntityNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
    
}
