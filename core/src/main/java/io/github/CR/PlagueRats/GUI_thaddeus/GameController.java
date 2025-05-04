package io.github.CR.PlagueRats.GUI_thaddeus;



import io.github.CR.PlagueRats.backend.*;

import java.util.List;

public enum GameController {
    INSTANCE;
    private TurnBasedGame game;
    private final TurnBasedGame backend = new TurnBasedGame();
    private final CommandManager commands = new CommandManager();

    // enqueue & execute a Move
    public void move(AbstractCharacter who, Cell where) {
        commands.addCommand(new MoveCommand(who, where));
    }

    // enqueue & execute an Attack
    public void attack(AbstractCharacter who, AbstractCharacter target) {
        commands.addCommand(new AttackCommand(who, target));
    }

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
