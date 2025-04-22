package com.thaddycat.gradletest.backend;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private final CommandHistory commandHistory;
    private final CommandPool<CommandMacro> commandPool;
    private final List<Command> currentCommands = new ArrayList<>();
    private final int maxHistorySize;

    public CommandManager() {
        this.maxHistorySize = 5; // Default value
        this.commandHistory = new CommandHistory();
        this.commandPool = new CommandPool<>(
                CommandMacro::new,
                0, // Initial pool size
                this.maxHistorySize // Maximum pool/history size
        );
    }

    public void addCommand(Command command) {
        this.currentCommands.add(command);
        System.out.println("Adding command: " + command);
        currentCommands.add(command);
    }

    public void execute() {
        if (this.currentCommands.isEmpty()) return;

        // Create a new macro for this step
        CommandMacro stepMacro = new CommandMacro();
        stepMacro.addAll(this.currentCommands);

        System.out.println("Executing commands: " + currentCommands.size());
        for (Command c : currentCommands) {
            System.out.println("Executing: " + c);

        }
        stepMacro.execute();

        // Add to history and enforce size limit
        this.commandHistory.push(stepMacro);
        if (this.commandHistory.size() > this.maxHistorySize) {
            this.commandHistory.removeOldest();
        }

        // Reset for the next step
        this.currentCommands.clear();
    }

    public void undo() {
        if (this.commandHistory.isEmpty()) {
            System.out.println("Undo History Empty: Nothing to undo!");
            return;
        }

        CommandMacro lastStep = this.commandHistory.pop();
        lastStep.undo();

        // Return to pool for reuse
        this.commandPool.release(lastStep);
    }
    public List<Command> getQueuedCommands() {
        return currentCommands;
    }

}
