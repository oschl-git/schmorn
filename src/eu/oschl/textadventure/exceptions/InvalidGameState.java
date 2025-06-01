package eu.oschl.textadventure.exceptions;

/**
 * Exception thrown when the game is in an invalid state.
 *
 * @author Ondřej Schlaichert
 */
public class InvalidGameState extends RuntimeException {
    public InvalidGameState() {
        super();
    }

    public InvalidGameState(String message) {
        super(message);
    }

    public InvalidGameState(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidGameState(Throwable cause) {
        super(cause);
    }
}
