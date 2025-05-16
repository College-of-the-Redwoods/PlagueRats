package io.github.CR.PlagueRats.backend;

import com.badlogic.gdx.Game;
import io.github.CR.PlagueRats.GUI_thaddeus.control.GameScreen;

public class TurnBasedGame extends Game {

    public TurnBasedGame() {
        MapGenerator.generateCellArray(5, 5);
        CharacterGenerator.loadCharacters();
    }

    public void executeStep() {
        System.out.println("Executing Game Step...");
        for (AbstractCharacter c : AbstractCharacter.getCharacterArrayList()) {
            System.out.println("Executing CommandManager for " + c.getName());
            c.getCommandManager().execute();
        }
        System.out.println("Game Step Successfully Executed. \n");
    }

    public void undoStep() {
        System.out.println("Undoing Game Step");
        for (AbstractCharacter c : AbstractCharacter.getCharacterArrayList()) {
            System.out.println(c.getName() + " undoing CommandManager");
            c.getCommandManager().undo();
        }
        System.out.println("Game Step Successfully Undone. \n");
    }

    @Override
    public void create() {
        this.setScreen(new GameScreen(this));
    }
}
