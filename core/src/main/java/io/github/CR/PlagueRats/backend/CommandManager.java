package io.github.CR.PlagueRats.backend;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private final CommandHistory commandHistory;
    private final CommandPool<CommandMacro> commandPool;
    private final List<Command> currentCommands = new ArrayList<>();
    private final int maxHistorySize;

    public CommandManager() {
        maxHistorySize = 5;
        commandHistory = new CommandHistory();
        commandPool = new CommandPool<>(
                CommandMacro::new,
                0,
                maxHistorySize
        );
    }

    public void addCommand(Command cmd) {
        currentCommands.add(cmd);
    }

    public List<Command> getCurrentCommands() {
        return currentCommands;
    }

    public void execute() {
        if (currentCommands.isEmpty()) return;

        // Create a new macro for this step
        CommandMacro stepMacro = new CommandMacro();
        stepMacro.addAll(currentCommands);

        System.out.println("Executing commands: " + currentCommands.size());
        for (Command c : currentCommands) {
            System.out.println("Executing: " + c);

        }
        stepMacro.execute();

        // Add to history and enforce size limit
        commandHistory.push(stepMacro);
        if (commandHistory.size() > maxHistorySize) {
            commandHistory.removeOldest();
        }

        // Reset for the next step
        currentCommands.clear();
    }

    public void undo() {
        if (commandHistory.isEmpty()) {
            System.out.println("Undo History Empty: Nothing to undo!");
            return;
        }

        CommandMacro lastStep = commandHistory.pop();
        lastStep.undo();

        // Return to pool for reuse
        commandPool.release(lastStep);
    }
}
