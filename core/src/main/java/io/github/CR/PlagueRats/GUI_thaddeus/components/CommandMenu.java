package io.github.CR.PlagueRats.GUI_thaddeus.components;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * CommandMenu
 * A simple popup (Scene2D Table) that appears at the click location.
 * It shows a button for each command available — currently Move and Attack—and runs the supplied callbacks when clicked.
 * When either action finishes, it also runs an onClose callback to hide itself.
 */

public class CommandMenu extends Table {
    public CommandMenu(Stage stage, Skin skin, float x, float y, Runnable onMove, Runnable onAttack, Runnable onClose) {
        super(skin);
        // position & background
        setPosition(x, y);
        setBackground("default-round");

        // header label
        Label label = new Label("Choose action:", skin);

        // “Move” button
        TextButton moveButton = new TextButton("Move", skin);
        moveButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onMove.run();
                onClose.run(); // hide menu when done
            }
        });

        // “Attack” button
        TextButton attackButton = new TextButton("Attack", skin);
        attackButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onAttack.run();
                onClose.run(); // hide menu when done
            }
        });

        // layout: label, move, attack
        add(label).pad(5);
        row();
        add(moveButton).pad(5);
        row();
        add(attackButton).pad(5);

        // finally add to the Stage so it’s visible
        stage.addActor(this);
    }
}
 /*
   Patterns:
     • Composite           ◀ Structural (Table holds multiple widgets)
     • Observer            ◀ Behavioral (ClickListeners on Move & Attack buttons)
 */
