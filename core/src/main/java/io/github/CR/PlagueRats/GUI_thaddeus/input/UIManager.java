package io.github.CR.PlagueRats.GUI_thaddeus.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import io.github.CR.PlagueRats.GUI_thaddeus.GameStage;
import io.github.CR.PlagueRats.GUI_thaddeus.StatsPanel;
import io.github.CR.PlagueRats.backend.*;

public class UIManager implements InputProcessor {
    private final GameStage gameStage;
    private final StatsPanel statsPanel;
    private AbstractCharacter selectedCharacter;

    public UIManager(GameStage gameStage, Skin skin) {
        this.gameStage  = gameStage;
        this.statsPanel = new StatsPanel(skin);

        // initially hidden
        statsPanel.setVisible(false);
        // position in the corner:
        statsPanel.setPosition(10, Gdx.graphics.getHeight() - statsPanel.getHeight() - 10);
        gameStage.addActor(statsPanel);
    }

    /** Called by your InputRouter before any world‐click handlers. */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button != Input.Buttons.LEFT) return false;
// convert from **screen** to **stage** coords:
        Vector2 stageCoords = gameStage.screenToStageCoordinates(new Vector2(screenX, screenY));
        // let Scene2D UI elements have first crack
        Actor hit = gameStage.hit(stageCoords.x, stageCoords.y, true);
        if (hit != null) {
            // dispatch into Stage (buttons, menus, etc)
            gameStage.touchDown(screenX, screenY, pointer, button);
            return true;  // consumed by UI
        }
        return false;     // not a UI click
    }

    // we don’t handle keyboard here; pass it along
    @Override public boolean keyDown(int keycode) {return false;}
    @Override public boolean keyUp(int keycode)   { return false; }
    @Override public boolean keyTyped(char character)       { return false; }
    @Override public boolean touchUp(int x, int y, int p, int b)     { return false; }
    @Override public boolean touchCancelled(int i, int i1, int i2, int i3) {return false; }
    @Override public boolean touchDragged(int x, int y, int p)       { return false; }
    @Override public boolean mouseMoved(int x, int y)               { return false; }
    @Override public boolean scrolled(float dx, float dy)           { return false; }

    //––– public API for the rest of your code to drive the stats panel –––//

    /** Call when a new character is selected in the world. */
    public void setSelectedCharacter(AbstractCharacter c) {
        this.selectedCharacter = c;
        statsPanel.update(c, "N/A");
        statsPanel.setVisible(c != null);
        if (c != null) {
            Gdx.app.log("UIManager", "Selected: " + c.getName());
        }
    }

    /** Clear both the model selection and the UI. */
    public void clearSelection() {
        this.selectedCharacter = null;
        statsPanel.update(null, null);
        statsPanel.setVisible(false);
    }

    /** Updates the “queued command” text in your panel. */
    public void updateCommandInfo(AbstractCharacter c, String info) {
        // only update if this character is still selected
        if (c != null && c == selectedCharacter) {
            statsPanel.update(c, info);
        }
    }
}
