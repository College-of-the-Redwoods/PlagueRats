package io.github.CR.PlagueRats;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class FirstMenu extends MainMenu {
    private final String[] buttonNames;


    public FirstMenu(final RatGame game) {


         buttonNames = new String[] {
            "Play", "Settings", "Quit"
        };

        Label title = new Label("Plague Rats", skin);
        title.setSize(400, 200);
        title.setPosition((float) Gdx.graphics.getWidth() / 2 - 50, (float) Gdx.graphics.getHeight() / 2);
        // Create "Play" button
        TextButton playButton = gFact.createTextButton(buttonNames[0], skin);

        // Create "Settings" button
        TextButton settingsButton = gFact.createTextButton(buttonNames[1], skin);

        TextButton quitButton = gFact.createTextButton(buttonNames[2], skin);

        // Add listener to the "Play" button
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game)); // Switch to the game screen
            }
        });

        // Add listener to the "Settings" button
        settingsButton.addListener(new ClickListener() {
           @Override
           public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new SettingsMenu(game));
           }
        });

        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.exit(0);
            }
        });
        // Add button to the stage
        stage.addActor(title);
        stage.addActor(playButton);
        stage.addActor(settingsButton);
        stage.addActor(quitButton);

    }

}
