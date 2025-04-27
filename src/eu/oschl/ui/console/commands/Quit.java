package eu.oschl.ui.console.commands;

public class Quit implements Command {
    @Override
    public String[] getTriggers() {
        return new String[]{
                "quit",
                "stop"
        };
    }

    @Override
    public String getDescription() {
        return "leave the game and stop playing";
    }

    @Override
    public void execute(String[] args) {
        System.exit(0);
    }
}
