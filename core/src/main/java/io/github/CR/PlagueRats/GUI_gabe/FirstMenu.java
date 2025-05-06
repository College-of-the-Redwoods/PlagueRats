package io.github.CR.PlagueRats.GUI_gabe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.github.CR.PlagueRats.backend.TurnBasedGame;

import java.util.concurrent.TimeUnit;

public class FirstMenu extends MainMenu {
    private final String[] buttonNames;
    private final Receiver r;
    private final Command quitCommand;
    private final Command settingsCommand;
    private Invoker i;

    public FirstMenu(final TurnBasedGame game) {

        buttonNames = new String[] {
            "Play", "Settings", "Quit"
        };

        r = new Receiver(game);

        Label title = new Label("Plague Rats", skin);
        title.setSize(400, 200);
        title.setPosition((float) Gdx.graphics.getWidth() / 2 - 50, (float) Gdx.graphics.getHeight() / 2);
        // Create "Play" button
        TextButton playButton = gFact.createTextButton(buttonNames[0], skin);

        // Create "Settings" button
        TextButton settingsButton = gFact.createTextButton(buttonNames[1], skin);
        settingsCommand = new GoToSettings(r);

        TextButton quitButton = gFact.createTextButton(buttonNames[2], skin);
        quitCommand = new QuitGame(r);

        // Add listener to the "Play" button
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectionSound.play();
                game.setScreen(new GameScreen(game)); // Switch to the game screen
            }
        });

        // Add listener to the "Settings" button
        settingsButton.addListener(new ClickListener() {
           @Override
           public void clicked(InputEvent event, float x, float y) {
               selectionSound.play();
               i = new Invoker(settingsCommand);
               i.execute();
           }
        });

        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                quitSound.play();
                try {
                    TimeUnit.MILLISECONDS.sleep(450);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                i = new Invoker(quitCommand);
                i.execute();
            }
        });
        // Add button to the stage
        stage.addActor(playButton);
        stage.addActor(settingsButton);
        stage.addActor(quitButton);

    }

}
