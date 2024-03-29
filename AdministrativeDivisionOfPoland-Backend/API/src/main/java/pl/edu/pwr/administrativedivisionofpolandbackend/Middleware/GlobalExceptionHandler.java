package pl.edu.pwr.administrativedivisionofpolandbackend.Middleware;

import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.edu.pwr.administrativedivisionofpolandbackend.Exceptions.AuthorizationException;
import pl.edu.pwr.administrativedivisionofpolandbackend.Exceptions.EntityInUseException;
import pl.edu.pwr.administrativedivisionofpolandbackend.Exceptions.InvalidReportRequestException;
import pl.edu.pwr.administrativedivisionofpolandbackend.Exceptions.InvalidRequestException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({InvalidReportRequestException.class})
    public ResponseEntity<Object> handleInvalidReportRequestException(InvalidReportRequestException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler({InvalidRequestException.class})
    public ResponseEntity<Object> handleInvalidArgumentException(InvalidRequestException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler({EntityInUseException.class})
    public ResponseEntity<Object> handleEntityInUseException(EntityInUseException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler({AuthorizationException.class})
    public ResponseEntity<Object> handleAuthException(AuthorizationException exception) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(exception.getMessage());
    }

    @ExceptionHandler({ExpiredJwtException.class})
    public ResponseEntity<Object> handleJTWException(ExpiredJwtException exception) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(exception.getMessage());
    }

    @ExceptionHandler({ClaimJwtException.class})
    public ResponseEntity<Object> handleJTWException(ClaimJwtException exception) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(exception.getMessage());
    }

}
