package eu.oschl.ui.console.exceptions;

/**
 * This exception is thrown when the input provided by the user is invalid.
 *
 * @author Ondřej Schlaichert
 */
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
