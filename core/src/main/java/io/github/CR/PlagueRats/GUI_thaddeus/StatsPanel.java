package io.github.CR.PlagueRats.GUI_thaddeus;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.github.CR.PlagueRats.backend.AbstractCharacter;

public class StatsPanel extends Table {
    private final Skin skin;
    private final Runnable onChangeCommand;

    public StatsPanel(Skin skin, Runnable onChangeCommand) {
        super();
        this.skin = skin;
        this.onChangeCommand = onChangeCommand;
        top().left();
    }

    /** Updates the labels completely. Always creates fresh ones. */
    public void update(AbstractCharacter c, String queuedCommand) {
        clearChildren(); // wipe everything

        if (c != null) {
            add(new Label("Queued: " + (queuedCommand != null ? queuedCommand : "N/A"), skin)).left().row();
            add(new Label("Name: " + c.getName(), skin)).left().row();
            add(new Label("HP: " + c.getResourcePoints().getHp(), skin)).left().row();
            // Show "Change Command" button if a command is queued
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
