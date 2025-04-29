package io.github.CR.PlagueRats.GUI_thaddeus;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import io.github.CR.PlagueRats.backend.*;


public class UIManager {
    private final StatsPanel statsPanel;
    private AbstractCharacter selectedCharacter;
    private final GameStage gameStage;

    public UIManager(GameStage gameStage, Skin skin) {
        this.gameStage = gameStage;
        statsPanel = new StatsPanel(skin);
        gameStage.addActor(statsPanel);
        statsPanel.update(null, null); // or however you clear it
    }
    public void setSelectedCharacter(AbstractCharacter character) {
        this.selectedCharacter = character;
        statsPanel.update(character, "N/A");
    }
    public AbstractCharacter getSelectedCharacter() {
        return selectedCharacter;
    }

    public void clearSelection() {
        this.selectedCharacter = null;
        statsPanel.update(null, null);
    }

    public StatsPanel getStatsPanel() {
        return statsPanel;
    }

    public void updateCommandInfo(AbstractCharacter character, String info) {
        statsPanel.update(selectedCharacter, "N/A");
    }
    public void updateCommandText(AbstractCharacter character, String text) {
        // update a label or internal state here
    }
}

