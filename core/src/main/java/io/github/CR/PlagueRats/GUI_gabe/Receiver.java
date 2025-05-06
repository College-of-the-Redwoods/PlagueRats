package io.github.CR.PlagueRats.GUI_gabe;

import io.github.CR.PlagueRats.backend.TurnBasedGame;

public class Receiver {
    private final TurnBasedGame game;

    public Receiver(TurnBasedGame game) {
        this.game = game;
    }

    public void quitGame() {
        System.exit(0);
    }

    public void settings() {
        game.setScreen(new SettingsMenu(game));
    }
}
