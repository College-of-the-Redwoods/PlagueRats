package com.thaddycat.gradletest.backend;

import java.util.List;
import java.util.stream.Collectors;


public class GameLauncher {
    public static void main(String[] args) {
        TurnBasedGame game = new TurnBasedGame();
        List<Cell> cellArray = MapGenerator.getCellArray();

        CharacterGenerator.loadCharacters();

        List<Character> characterList = Character.getCharacterArrayList();

        Character Jane = Character.getCharacterArrayList().get(0);
        Character John = characterList.get(characterList.size() - 1);

        for ( int i = 0; i <= 10; i++) {
            John.getCommandManager().addCommand(new MoveCommand(John, cellArray.get(i+1)));
            Jane.getCommandManager().addCommand(new MoveCommand(Jane, cellArray.get(i+3)));
            game.executeStep();
        }

        for ( int i = 0; i <= 6; i++) {
            game.undoStep();
        }

        for ( int i = 0; i <= 3; i++) {
            John.getCommandManager().addCommand(new AttackCommand(John, Jane));
            Jane.getCommandManager().addCommand(new AttackCommand(Jane, John));
            game.executeStep();
        }

        for ( int i = 0; i <= 4; i++) {
            game.undoStep();
        }

        CharacterGenerator.saveCharacters();
    }
}
