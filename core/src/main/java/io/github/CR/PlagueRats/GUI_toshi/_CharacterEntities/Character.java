package io.github.CR.PlagueRats.GUI_toshi._CharacterEntities;

public class Character implements ICharacter {
    private String name;
    private String stats;
    private int charId;

    public Character(int charId) {
        this.charId = charId;
        this.name = loadName();
        this.stats = loadStats();
        loadInventory(); // side-effect only in this example
    }

    private String loadName() {
        return "Rat Soldier " + charId;
    }

    private String loadStats() {
        return "HP: 100, Attack: 15";
    }

    private void loadInventory() {
        // Simulate inventory loading
        System.out.println("Loading inventory for character " + charId);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getStats() {
        return stats;
    }

    @Override
    public void performAction(String action) {
        System.out.println(name + " performs action: " + action);
    }
}

