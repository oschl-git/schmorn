package eu.oschl.textadventure.exceptions;

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
