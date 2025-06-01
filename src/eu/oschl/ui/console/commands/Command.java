package eu.oschl.ui.console.commands;

/***
 * This interface defines the structure for commands in the console application.
 *
 * @author Ondřej Schlaichert
 */
public interface Command {
    String[] getTriggers();
    String getDescription();
    void execute(String[] args);
}
