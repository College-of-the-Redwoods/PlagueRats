package io.github.CR.PlagueRats.GUI_thaddeus;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.thaddycat.gradletest.backend.GameCharacter;


public class UIManager {
    private final StatsPanel statsPanel;
    private GameCharacter selectedCharacter;
    private final GameStage gameStage;

    public UIManager(GameStage gameStage, Skin skin) {
        this.gameStage = gameStage;
        statsPanel = new StatsPanel(skin);
        gameStage.addActor(statsPanel);
        statsPanel.update(null, null); // or however you clear it
    }
    public void setSelectedCharacter(GameCharacter character) {
        this.selectedCharacter = character;
        statsPanel.update(character, "N/A");
    }
    public GameCharacter getSelectedCharacter() {
        return selectedCharacter;
    }

    public void clearSelection() {
        this.selectedCharacter = null;
        statsPanel.update(null, null);
    }

    public StatsPanel getStatsPanel() {
        return statsPanel;
    }

    public void updateCommandInfo(GameCharacter character, String info) {
        statsPanel.update(selectedCharacter, "N/A");
    }
    public void updateCommandText(GameCharacter character, String text) {
        // update a label or internal state here
    }
}

