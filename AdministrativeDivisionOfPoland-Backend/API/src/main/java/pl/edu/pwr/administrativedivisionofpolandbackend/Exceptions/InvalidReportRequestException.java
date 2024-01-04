package pl.edu.pwr.administrativedivisionofpolandbackend.Exceptions;

public class InvalidReportRequestException extends Exception {
    public InvalidReportRequestException(String message) {
        super(message);
    }
}