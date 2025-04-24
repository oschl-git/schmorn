package eu.oschl.textadventure.exceptions;

public class IncorrectGameSetupExeception extends RuntimeException {
    public IncorrectGameSetupExeception() {
        super();
    }

    public IncorrectGameSetupExeception(String message) {
        super(message);
    }

    public IncorrectGameSetupExeception(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectGameSetupExeception(Throwable cause) {
        super(cause);
    }
}
