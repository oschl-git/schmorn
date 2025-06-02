package eu.oschl.ui.console.commands;

/***
 * This interface defines the structure for commands in the console application.
 *
 * @author Ondřej Schlaichert
 */
public interface Command {
    /**
     * Returns an array of strings that trigger this command.
     *
     * @return an array of trigger strings
     */
    String[] getTriggers();

    /**
     * Returns a description of what the command does.
     *
     * @return a string description of the command
     */
    String getDescription();

    /**
     * Executes the command with the provided arguments.
     *
     * @param args an array of strings representing the command arguments
     */
    void execute(String[] args);
}
