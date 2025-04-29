package io.github.CR.PlagueRats.backend;

import java.util.ArrayList;
import java.util.List;

public class CommandMacro implements Command {
    private final List<Command> commands = new ArrayList<>();

    public void add(Command cmd) {
        commands.add(cmd);
    }

    public void addAll(List<Command> cmds) {
        commands.addAll(cmds);
    }

    public void remove(Command cmd) {
        commands.remove(cmd);
    }

    @Override
    public void execute() {
        for (Command c : commands) {
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
        commands.clear();
    }
}
