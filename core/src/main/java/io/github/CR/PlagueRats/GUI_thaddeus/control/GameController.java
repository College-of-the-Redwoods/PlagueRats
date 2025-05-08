package io.github.CR.PlagueRats.GUI_thaddeus.control;

import io.github.CR.PlagueRats.backend.*;
import java.util.List;
/**
 * GameController (Singleton enum)->
 * Central facade for issuing Move and Attack commands,
 * delegating to each character’s CommandManager, then
 * stepping or undoing via the backend TurnBasedGame.
 */
public enum GameController {
    INSTANCE;

    private final TurnBasedGame backend = new TurnBasedGame();
    private final CommandManager commands = new CommandManager();

    //  // Enqueue a MOVE command for 'who' to 'where'
    public void move(AbstractCharacter who, Cell where) {
        who.getCommandManager().addCommand(new MoveCommand(who, where));
    }
    // Enqueue an ATTACK command for 'who' against 'target'
    public void attack(AbstractCharacter who, AbstractCharacter target) {
        who.getCommandManager().addCommand(new AttackCommand(who, target));
    }
    // Execute all queued commands for each character
    public void step() {
        backend.executeStep();
    }

    // Undo the last step for each character
    public void undo() {
        backend.undoStep();
    }

    // Retrieve the current backend command queue (mostly for UI inspection)
    public List<Command> getCurrentCommands() {
        // Note: this references a local CommandManager only if you wire it.
        return commands.getCurrentCommands();
    }
}

/*
 * Patterns:
 *    • Singleton           ◀ Creational  (enum‐based singleton)
 *    • Facade              ◀ Structural (unified API over backend game & commands)
 *    • Command             ◀ Behavioral (queues and invokes Command objects)
 */
