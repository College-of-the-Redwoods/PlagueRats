package io.github.CR.PlagueRats.GUI_thaddeus.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import io.github.CR.PlagueRats.GUI_thaddeus.record.CommandRecord;
import io.github.CR.PlagueRats.GUI_thaddeus.control.GameStage;
import io.github.CR.PlagueRats.GUI_thaddeus.components.StatsPanel;
import io.github.CR.PlagueRats.backend.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * UIManager
 * ->
 * Bridges raw input and the StatsPanel UI:
 *   • intercepts left‐clicks on UI elements
 *   • tracks selection via setSelectedCharacter(...)
 *   • records CommandRecord history
 *   • updates StatsPanel on each change
 */
public class UIManager implements InputProcessor {
    private final GameStage gameStage;
    private final StatsPanel statsPanel;
    private final List<CommandRecord> history = new ArrayList<>();
    private AbstractCharacter selectedCharacter;

    public UIManager(GameStage gameStage, Skin skin) {
        this.gameStage = gameStage;
        this.statsPanel = new StatsPanel(skin, () -> {
            AbstractCharacter sel = this.selectedCharacter;
            if (sel != null) {
                clearCommandFor(sel);
            }
        });

        // initially hidden
        statsPanel.setVisible(false);
        // position in the corner:
        statsPanel.setPosition(10, Gdx.graphics.getHeight() - statsPanel.getHeight() - 10);
        gameStage.addActor(statsPanel);
    }

    /**
     * Called by InputRouter before any world‐click handlers.
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        // Let Scene2D UI elements handle clicks first
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
    /** Add or replace the record for this actor */
    public void record(CommandRecord rec) {
        history.removeIf(r -> r.actor == rec.actor); // <- enforce one-per-character if you like(remove new command if one exists for the charcter already):
        history.add(rec);
        // update stats panel
        updateCommandInfo(rec.actor, rec.toString());
    }
    /** Expose an unmodifiable view for rendering Command icons */
    public List<CommandRecord> getHistory() {
        return Collections.unmodifiableList(history);
    }
    //––– public API for the rest of your code to drive the stats panel –––//

    /** Returns the currently selected PC (or null). */
    public AbstractCharacter getSelectedCharacter() {
        return selectedCharacter;
    }
    /** Call when a new character is selected in the world. */
    public void setSelectedCharacter(AbstractCharacter c) {
        this.selectedCharacter = c;

        // Look up any queued CommandRecord for this character
        String info = "N/A";
        for (CommandRecord rec : history) {
            if (rec.actor == c) {
                info = rec.toString();
                break;
            }
        }
        statsPanel.update(c, info);
        statsPanel.setVisible(c != null);

        if (c != null) {
            Gdx.app.log("UIManager", "Selected: " + c.getName() + ", queued: " + info);
        }
    }
    /** Clear both the model selection and the UI. */
    public void clearSelection() {
        this.selectedCharacter = null;
        statsPanel.update(null, null);
        statsPanel.setVisible(false);
    }
    /** Clear all the queued-command previews (but keep the same selection). */
    public void clearCommandPreviews() {
        history.clear();
        if (selectedCharacter != null) {
            statsPanel.update(selectedCharacter, "N/A");
        }
    }
    private void clearCommandFor(AbstractCharacter sel) {
        // Remove sel’s record
        history.removeIf(r -> r.actor == sel);
        // Update stats panel
        statsPanel.update(sel, "N/A");
    }
    /** Updates the “queued command” text in your panel. */
    public void updateCommandInfo(AbstractCharacter c, String info) {
        // only update if this character is still selected
        if (c != null && c == selectedCharacter) {
            statsPanel.update(c, info);
        }
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

}
