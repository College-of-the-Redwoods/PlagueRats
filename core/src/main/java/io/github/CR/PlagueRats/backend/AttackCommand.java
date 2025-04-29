package io.github.CR.PlagueRats.backend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class AttackCommand implements Command {
    private final GameCharacter attacker;
    private GameCharacter defender;
    private int previousHP;
    private boolean executed;

    public AttackCommand(GameCharacter attacker, GameCharacter target) {
        this.attacker = attacker;
        this.defender = target;

        this.executed = false;
    }

    // only using to get current logic to work. Do not use this, it will be deleted.
    public GameCharacter getDefender() {
        return defender;
    }

    @Override
    public void execute() {
        if (!this.executed) {
            System.out.println(this.defender.getName() + " is being attacked by " + this.attacker.getName() + " for 10 damage.");
            ResourcePoints defenderResourcePoints = this.defender.getResourcePoints();
            this.previousHP = defenderResourcePoints.getHp();
            int newHP = this.previousHP - 10; // Placeholder calculation
            System.out.println(this.defender.getName() + "'s health has dropped from " + this.previousHP + " down to " + newHP);

            Sound attackSound = Gdx.audio.newSound(Gdx.files.internal("attack.mp3"));
            attackSound.play(1.0f);

            if (newHP <= 0) {
                System.out.println(this.defender.getName() + " is dead!");
                // Death logic
            }
            defenderResourcePoints.setHp(newHP);
            this.executed = true;
        }
    }

    @Override
    public void undo() {
        if (this.executed) {
            this.defender.getResourcePoints().setHp(this.previousHP);
            System.out.printf("Undo: %s's HP reverted to %d%n", this.defender.getName(), this.previousHP);
        }
            this.executed = false;
        }

    @Override
    public void reset() {
        this.defender = null;
        this.previousHP = 0;
    }
}
