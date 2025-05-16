package io.github.CR.PlagueRats.GUI_thaddeus.record;


import io.github.CR.PlagueRats.backend.AbstractCharacter;
import io.github.CR.PlagueRats.backend.Cell;
import io.github.CR.PlagueRats.backend.Position;

/**
 * CommandRecord
 * ->
 * An immutable, serializable record of a single command choice:
 *   • Type.MOVE   – move an actor to a specific cell
 *   • Type.ATTACK – attack another actor
 * -
 * Each instance captures:
 *   • the command type
 *   • the actor issuing the command
 *   • the target cell (for MOVE) or target character (for ATTACK)
 * -
 * The toString() method returns a human-readable description
 * (e.g. "Move to [x, y]" or "Attack Jane").
 */
public class CommandRecord {
    public enum Type { MOVE, ATTACK }

    public final Type    type;
    public final AbstractCharacter actor;
    public final Cell cellTarget;                   // only non-null for MOVE
    public final AbstractCharacter charTarget;     // only non-null for ATTACK

    @Override
    public String toString() {
        // Generate a description based on type and non-null fields
        if (type == Type.MOVE && cellTarget != null) {
            Position pos = cellTarget.getPosition();
            return "Move to [" + pos.x + ", " + pos.y + "]";
        } else if (type == Type.ATTACK && charTarget != null) {
            return "Attack " + charTarget.getName();
        }
        return "N/A";
    }

    // Private constructor to enforce use of factory methods below
    private CommandRecord(Type type, AbstractCharacter actor, Cell c, AbstractCharacter t) {
        this.type       = type;
        this.actor      = actor;
        this.cellTarget = c;
        this.charTarget = t;
    }

    /** Create a MOVE record for actor → cell c */
    public static CommandRecord move(AbstractCharacter actor, Cell c) {
        return new CommandRecord(Type.MOVE, actor, c, null);
    }

    /** Create an ATTACK record for actor → target */
    public static CommandRecord attack(AbstractCharacter actor, AbstractCharacter target) {
        return new CommandRecord(Type.ATTACK, actor, null, target);
    }
}
/*
 * Patterns:
 *   • Command       ◀ Behavioral (encapsulates a user’s command choice)
 *   • Factory       ◀ Creational (static factory methods move(...) and attack(...))
 */
