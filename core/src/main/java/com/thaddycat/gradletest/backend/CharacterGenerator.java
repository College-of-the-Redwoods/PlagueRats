package com.thaddycat.gradletest.backend;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class CharacterGenerator {
    // Load character from .txt file
    public static Character loadFromFile(String filePath) throws IOException {
        boolean isPC = false;
        String name = "";
        ResourcePoints resourcePoints = new ResourcePoints();
        Position position = new Position();
        List<String> equipment = new ArrayList<>();
        String spritePath = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(": ", 2); // Split into key-value pairs
                if (parts.length != 2) continue;

                String key = parts[0].trim();
                String value = parts[1].trim();

                switch (key) {
                    case "PC":
                        isPC = Boolean.parseBoolean(value);
                        break;
                    case "Name":
                        name = value;
                        break;
                    case "HP":
                        resourcePoints.setHp(Integer.parseInt(value));
                        break;
                    case "MP":
                        resourcePoints.setMp(Integer.parseInt(value));
                        break;
                    case "AP":
                        resourcePoints.setAp(Integer.parseInt(value));
                        break;
                    case "XP":
                        resourcePoints.setXp(Integer.parseInt(value));
                        break;
                    case "SP":
                        resourcePoints.setSp(Integer.parseInt(value));
                        break;
                    case "Position":
                        String[] coords = value.replaceAll("[^0-9,]", "") // Remove non-numeric chars
                                .split(",");
                        position.setPosition(Integer.parseInt(coords[0].trim()), Integer.parseInt(coords[1].trim()));
                        break;
                    case "Equipment":
                        if (!value.isEmpty()) {
                            String[] items = value.split(", ");
                            for (String item : items) equipment.add(item.trim());
                        }
                        break;
                    case "sprite":
                        spritePath = value;
                        break;
                }
            }
        }

        // Create PC/NPC based on the "PC" flag
        if (spritePath == null || spritePath.isEmpty()) {
            System.err.println("[WARNING] spritePath missing for " + name + ", using default.");
            spritePath = "default.png"; // or any fallback you have
        }

        Character character;
        if (isPC) {
            character = new PCCharacter(name, position, resourcePoints, spritePath);
            System.out.println("[DEBUG] Loaded PC: " + name + ", sprite: " + spritePath);
        } else {
            character = new NPCCharacter(name, position, resourcePoints, spritePath);
            System.out.println("[DEBUG] Loaded NPC: " + name + ", sprite: " + spritePath);
        }

        character.setSpritePath(spritePath);

        character.getInventory().addAll(equipment);
        return character;
    }

    // Save character to .txt file
    public static void saveToFile(Character character, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("PC: " + (character instanceof PCCharacter)); // Detect PC/NPC
            writer.newLine();
            writer.write("Name: " + character.getName());
            writer.newLine();
            writer.write("HP: " + character.getResourcePoints().getHp());
            writer.newLine();
            writer.write("MP: " + character.getResourcePoints().getMp());
            writer.newLine();
            writer.write("AP: " + character.getResourcePoints().getAp());
            writer.newLine();
            writer.write("XP: " + character.getResourcePoints().getXp());
            writer.newLine();
            writer.write("SP: " + character.getResourcePoints().getSp());
            writer.newLine();
            writer.write("Position: x = " + character.getPosition().getX() + ", y = " + character.getPosition().getY());
            writer.newLine();
            writer.write("Equipment: " + String.join(", ", character.getInventory()));
            writer.newLine();
            writer.write("sprite: " + character.getSpritePath());
            writer.newLine();

        }
    }

    public static void saveCharacters() {
        for (Character c : Character.getCharacterArrayList()) {
            try {
                CharacterGenerator.saveToFile(c, "saves/" + c.getName() + "_save.txt");
                System.out.println("Saved: " + c.getName());
            } catch (IOException e) {
                System.err.println("Failed to save " + c.getName() + ": " + e.getMessage());
            }
        }
    }

    public static void loadCharacters() {
        System.out.println("[DEBUG] Working directory: " + new java.io.File(".").getAbsolutePath());

        File savesDir = new File("assets/saves");
        if (!savesDir.exists() || !savesDir.isDirectory()) {
            System.err.println("Saves directory not found!");
            return;
        }

        File[] saveFiles = savesDir.listFiles((dir, name) -> name.endsWith(".txt"));
        if (saveFiles == null) return;

        for (File file : saveFiles) {
            try {
                System.out.println("Loading next character...");
                Character loadedChar = CharacterGenerator.loadFromFile(file.getPath());
                System.out.println("Loaded: " + loadedChar.getName() + ". \n");
            } catch (IOException e) {
                System.err.println("Failed to load " + file.getName() + ": " + e.getMessage());
            }
        }
    }
}
