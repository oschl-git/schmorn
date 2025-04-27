package eu.oschl.ui.console.exceptions;

public class InvalidInput extends Exception {
    public InvalidInput() {
        super();
    }

    public InvalidInput(String message) {
        super(message);
    }

    public InvalidInput(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidInput(Throwable cause) {
        super(cause);
    }
}
