package com.thaddycat.gradletest.backend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;
import java.util.List;

public abstract class Character {
    private static final List<Character> characterArrayList = new ArrayList<>();
    private final List<String> inventory = new ArrayList<>();
    private final CommandManager commandManager;
    private final ResourcePoints resourcePoints;
    private final Position position;
    private String name;
    private String spritePath;
    private Texture texture;
    private Sprite sprite;


    public Character(String name, Position position, ResourcePoints resourcePoints, String spritePath) {
        this.name = name;
        this.position = position;
        this.resourcePoints = resourcePoints;
        this.commandManager = new CommandManager();
        this.spritePath = spritePath;

        if (spritePath == null || spritePath.isEmpty()) {
            throw new IllegalArgumentException("spritePath is null or empty!");
        }

        this.texture = new Texture(Gdx.files.internal(spritePath));
        this.sprite = new Sprite(this.texture);

        Cell initialCell = MapGenerator.getCellAt(this.position.getX(), this.position.getY());
        if (initialCell != null && !initialCell.isOccupied()) {
            initialCell.occupyCell();
        }

        characterArrayList.add(this);
    }

    public static List<Character> getCharacterArrayList() { return characterArrayList; }

    public CommandManager getCommandManager() { return this.commandManager; }

    public void setPosition(int x, int y) { position.setPosition(x, y); }

    public Position getPosition() { return this.position; }

    public void setName(String name) { this.name = name; } // If this is implemented, we will use the old name to find the latest save file and rename it with the new name.

    public String getName() { return this.name; }

    public ResourcePoints getResourcePoints() { return this.resourcePoints; }

    public List<String> getInventory() {
        return inventory;
    }

    public String getSpritePath() { return spritePath; }

    public void setSpritePath(String spritePath) {
        this.spritePath = spritePath;
        this.texture = new Texture(Gdx.files.internal(spritePath));
        this.sprite = new Sprite(texture);
    }

    public Texture getTexture() {
        return texture;
    }

    public Sprite getSprite() {
        return sprite;
    }


}
