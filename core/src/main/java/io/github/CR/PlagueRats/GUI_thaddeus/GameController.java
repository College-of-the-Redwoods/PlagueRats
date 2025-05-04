package io.github.CR.PlagueRats.GUI_thaddeus;



import io.github.CR.PlagueRats.backend.Command;
import io.github.CR.PlagueRats.backend.TurnBasedGame;
import io.github.CR.PlagueRats.backend.CommandManager;

import java.util.List;

public enum GameController {
    INSTANCE;
    private TurnBasedGame game;
    private final TurnBasedGame backend = new TurnBasedGame();
    private final CommandManager commands = new CommandManager();

    public void step() {
        backend.executeStep();
    }

    public void undo() {
        backend.undoStep();
    }

    public List<Command> getQueue() {
        return commands.getCurrentCommands();
    }
}
