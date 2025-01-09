package command;

/**
 * Interface defining the contract for game commands
 */
public interface Command {
    /**
     * Execute the command
     */
    void execute();

    /**
     * Undo the command
     */
    void undo();
}