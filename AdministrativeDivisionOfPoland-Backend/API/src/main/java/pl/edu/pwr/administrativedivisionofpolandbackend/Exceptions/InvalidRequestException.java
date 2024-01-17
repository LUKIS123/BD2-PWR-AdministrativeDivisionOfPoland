package pl.edu.pwr.administrativedivisionofpolandbackend.Exceptions;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String message) {
        super(message);
    }
}
