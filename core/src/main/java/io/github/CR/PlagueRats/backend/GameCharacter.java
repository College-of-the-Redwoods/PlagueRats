package io.github.CR.PlagueRats.backend;

import java.util.ArrayList;
import java.util.List;

public abstract class GameCharacter {
    private static final List<GameCharacter> characterArrayList = new ArrayList<>();
    private final List<String> inventory = new ArrayList<>();
    private final CommandManager commandManager;
    private final ResourcePoints resourcePoints;
    private final Position position;
    private String name;

    public GameCharacter(String name, Position position, ResourcePoints resourcePoints) {
        this.name = name;
        this.position = position;
        this.resourcePoints = resourcePoints;
        commandManager = new CommandManager();
        characterArrayList.add(this);
    }

    public static List<GameCharacter> getCharacterArrayList() { return characterArrayList; }

    public CommandManager getCommandManager() { return commandManager; }

    public void setPosition(int x, int y) { position.set(x, y); }

    public Position getPosition() { return position; }

    public void setName(String name) { this.name = name; }

    public String getName() { return name; }

    public ResourcePoints getResourcePoints() { return resourcePoints; }

    public List<String> getInventory() {
        return inventory;
    }
}
