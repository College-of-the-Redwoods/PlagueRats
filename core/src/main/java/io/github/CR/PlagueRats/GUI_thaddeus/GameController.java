package io.github.CR.PlagueRats.GUI_thaddeus;

import com.thaddycat.gradletest.backend.Command;
import com.thaddycat.gradletest.backend.TurnBasedGame;

import java.util.List;

public enum GameController {
    INSTANCE;
    private TurnBasedGame game;
    private final TurnBasedGame backend = new TurnBasedGame();

    public void step() {
        backend.executeStep();
    }

    public void undo() {
        backend.undoStep();
    }

    public List<Command> getQueue() {
        return backend.getCommandQueue();
    }
}
