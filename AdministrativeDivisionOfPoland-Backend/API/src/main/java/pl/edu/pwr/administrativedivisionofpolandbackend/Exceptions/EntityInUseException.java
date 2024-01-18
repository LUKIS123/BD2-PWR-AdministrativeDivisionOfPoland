package pl.edu.pwr.administrativedivisionofpolandbackend.Exceptions;

public class EntityInUseException extends Exception {
    public EntityInUseException(String message) {
        super(message);
    }
}
