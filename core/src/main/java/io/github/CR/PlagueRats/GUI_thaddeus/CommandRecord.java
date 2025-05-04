package io.github.CR.PlagueRats.GUI_thaddeus;


import io.github.CR.PlagueRats.backend.AbstractCharacter;
import io.github.CR.PlagueRats.backend.Cell;

public class CommandRecord {
    public enum Type { MOVE, ATTACK }

    public final Type    type;
    public final AbstractCharacter actor;
    public final Cell cellTarget;     // for MOVE
    public final AbstractCharacter charTarget;     // for ATTACK

    private CommandRecord(Type type, AbstractCharacter actor, Cell c, AbstractCharacter t) {
        this.type       = type;
        this.actor      = actor;
        this.cellTarget = c;
        this.charTarget = t;
    }

    public static CommandRecord move(AbstractCharacter actor, Cell c) {
        return new CommandRecord(Type.MOVE, actor, c, null);
    }
    public static CommandRecord attack(AbstractCharacter actor, AbstractCharacter target) {
        return new CommandRecord(Type.ATTACK, actor, null, target);
    }
}
