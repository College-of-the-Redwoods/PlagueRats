package com.thaddycat.gradletest;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class CommandMenu extends Table {
    public CommandMenu(Stage stage, Skin skin, float x, float y, Runnable onMove, Runnable onAttack) {
        super(skin);
        setPosition(x, y);
        setBackground("default-round");

        Label label = new Label("Choose action:", skin);

        TextButton moveButton = new TextButton("Move Here", skin);
        TextButton attackButton = new TextButton("Attack Here", skin);

        moveButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onMove.run();
                remove(); // Close menu
            }
        });

        attackButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onAttack.run();
                remove(); // Close menu
            }
        });

        add(moveButton).pad(5);
        row();
        add(attackButton).pad(5);

        stage.addActor(this);
    }
}
