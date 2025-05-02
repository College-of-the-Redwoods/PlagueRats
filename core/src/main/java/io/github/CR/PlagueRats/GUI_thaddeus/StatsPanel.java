package io.github.CR.PlagueRats.GUI_thaddeus;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import io.github.CR.PlagueRats.backend.AbstractCharacter;
import io.github.CR.PlagueRats.backend.ResourcePoints;

public class StatsPanel extends Table {
    private final Skin skin;

    public StatsPanel(Skin skin) {
        super();
        this.skin = skin;
        top().left();
    }

    /** Updates the labels completely. Always creates fresh ones. */
    public void update(AbstractCharacter c, String queuedCommand) {
        clearChildren(); // wipe everything

        if (c != null) {
            add(new Label("Queued: " + (queuedCommand != null ? queuedCommand : "N/A"), skin)).left().row();
            add(new Label("Name: " + c.getName(), skin)).left().row();
            add(new Label("HP: " + c.getResourcePoints().getHp(), skin)).left().row();
        } else {
            // optionally don't show anything at all if nothing is selected
            // (or add blank labels if you want consistent height)
        }
    }
}
