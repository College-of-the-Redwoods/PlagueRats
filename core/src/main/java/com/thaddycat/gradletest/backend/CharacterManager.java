package com.thaddycat.gradletest.backend;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CharacterManager {
    public static void saveCharacters() {
        CharacterGenerator.saveCharacters();
    }

    public static List<Character> loadCharacters() {
        File savesDir = new File("saves");

        System.out.println("[DEBUG] Absolute path to saves folder: " + savesDir.getAbsolutePath());
        System.out.println("[DEBUG] Exists? " + savesDir.exists() + ", Is Directory? " + savesDir.isDirectory());

        List<Character> loadedCharacters = new ArrayList<>();


        if (!savesDir.exists() || !savesDir.isDirectory()) {
            System.err.println("Saves directory not found!");
            return loadedCharacters;
        }

        File[] saveFiles = savesDir.listFiles((dir, name) -> name.endsWith(".txt"));
        if (saveFiles == null) return loadedCharacters;

        for (File file : saveFiles) {
            try {
                System.out.println("Loading next character...");
                Character loadedChar = CharacterGenerator.loadFromFile(file.getPath());
                loadedCharacters.add(loadedChar);
                System.out.println("Loaded: " + loadedChar.getName() + ".\n");
            } catch (IOException e) {
                System.err.println("Failed to load " + file.getName() + ": " + e.getMessage());
            }
        }

        return loadedCharacters;
    }
}
