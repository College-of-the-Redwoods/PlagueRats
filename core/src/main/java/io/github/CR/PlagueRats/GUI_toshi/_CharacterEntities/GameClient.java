package io.github.CR.PlagueRats.GUI_toshi._CharacterEntities;

public class GameClient {
    public static void main(String[] args) {
        ICharacter char1 = new CharacterProxy(1);
        ICharacter char2 = new CharacterProxy(2);

        // No actual loading yet
        System.out.println("Characters created");

        // Trigger full load of character 1
        System.out.println(char1.getName());
        char1.performAction("Attack");

        // Trigger full load of character 2
        System.out.println(char2.getStats());
        char2.performAction("Defend");
    }
}

