package com.thaddycat.gradletest.backend;

import java.util.ArrayList;
import java.util.List;

public class CommandMacro implements Command {
    private final List<Command> commands = new ArrayList<>();

    public void add(Command cmd) {
        this.commands.add(cmd);
    }

    public void addAll(List<Command> commands) {
        this.commands.addAll(commands);
    }

    public void remove(Command cmd) {
        this.commands.remove(cmd);
    }

    @Override
    public void execute() {
        for (Command c : this.commands) {
            c.execute();
        }
    }

    @Override
    public void undo() {
        for (int i = commands.size() - 1; i >= 0; i--) {
            commands.get(i).undo();
        }
    }

    @Override
    public void reset() {
        this.commands.clear();
    }
}
