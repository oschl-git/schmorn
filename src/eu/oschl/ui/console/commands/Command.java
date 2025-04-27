package eu.oschl.ui.console.commands;

public interface Command {
    String[] getTriggers();
    String getDescription();
    void execute(String[] args);
}
