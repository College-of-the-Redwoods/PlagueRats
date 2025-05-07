package io.github.CR.PlagueRats.GUI_thaddeus.components;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.github.CR.PlagueRats.backend.AbstractCharacter;

/**
 * StatsPanel
 * ->
 * A UI panel (Scene2D Table) shown in the corner that displays:
 *   • the text of the selected character's queued command (or “N/A”)
 *   • the selected character’s name
 *   • the selected character’s HP
 *   • a “Change Command” button that lets you clear & re-choose
 */

public class StatsPanel extends Table {
    private final Skin skin;
    private final Runnable onChangeCommand;

    public StatsPanel(Skin skin, Runnable onChangeCommand) {
        super();  // currently use a bare Table (no built-in background) and we lay out our own labels/buttons
        this.skin = skin;
        this.onChangeCommand = onChangeCommand;
        top().left();  // place in upper-left corner
    }

    /**
     * Rebuilds the entire panel based on:
     * @param c the currently selected character (or null)
     * @param queuedCommand the text to show under “Queued:”, or null/N/A
     */
    public void update(AbstractCharacter c, String queuedCommand) {
        clearChildren(); // remove old labels/buttons

        if (c != null) {
            // Queued Action row
            add(new Label("Queued: " + (queuedCommand != null ? queuedCommand : "N/A"), skin))
                .left().row();

            // Name row
            add(new Label("Name: " + c.getName(), skin))
                .left().row();
            // HP row
            add(new Label("HP: " + c.getResourcePoints().getHp(), skin))
                .left().row();

            // If there *is* a queued action, show Change Command button
            if (queuedCommand != null && !queuedCommand.equals("N/A")) {
                TextButton changeBtn = new TextButton("Change Command", skin);
                changeBtn.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        onChangeCommand.run();
                    }
                });
                add(changeBtn).left().row();
            }
        }
    }
}
 /*
 * Patterns:
 *   • Composite           ◀ Structural (Table of Labels & optional Change‐Command button)
 *   • Observer            ◀ Behavioral (ClickListener on Change Command button)
 */
