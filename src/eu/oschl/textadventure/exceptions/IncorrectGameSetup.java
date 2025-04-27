package eu.oschl.textadventure.exceptions;

public class IncorrectGameSetup extends RuntimeException {
    public IncorrectGameSetup() {
        super();
    }

    public IncorrectGameSetup(String message) {
        super(message);
    }

    public IncorrectGameSetup(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectGameSetup(Throwable cause) {
        super(cause);
    }
}
