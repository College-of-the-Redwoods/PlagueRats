package io.github.CR.PlagueRats.GUI_gabe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import io.github.CR.PlagueRats.backend.TurnBasedGame;

import java.util.concurrent.TimeUnit;

public class FirstMenu implements Screen {
    private final Receiver r;
    private final Command quitCommand;
    private Invoker i;
    private Window window;
    private Skin skin;
    private Sound selectionSound;
    private Sound quitSound;
    private Stage stage;

    public FirstMenu(final TurnBasedGame game) {
        stage = new Stage(new FitViewport(640, 480));
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));


        window = new Window("", skin);


        selectionSound = Gdx.audio.newSound(Gdx.files.internal("audio/sfx/menu_select.wav"));
        quitSound = Gdx.audio.newSound(Gdx.files.internal("audio/sfx/menu_close.wav"));


        r = new Receiver(game);

        quitCommand = new QuitGame(r);

        Table root = new Table(skin);
        root.defaults().grow().pad(20f);
        window = new Window("", skin);
        window.defaults().grow().pad(5f);


        TextButton button = new TextButton("Play", skin);

        // Add listener to the "Play" button
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectionSound.play();
                game.setScreen(new GameScreen(game)); // Switch to the game screen
            }
        });
        window.add(button);
        window.row();

        button = new TextButton("Settings", skin);

        // Add listener to the "Settings" button
        button.addListener(new ClickListener() {
           @Override
           public void clicked(InputEvent event, float x, float y) {
               selectionSound.play();
//               game.setScreen(new SettingsMenu(game));
           }
        });
        window.add(button);
        window.row();

        button = new TextButton("Quit", skin);
        button.addListener(new ClickListener() {
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
        window.add(button);
        // Add button to the stage

        root.add(window).expand().center();
        window.addAction(Actions.sequence(Actions.alpha(0f), Actions.fadeIn(2f)));
        stage.addActor(root);

        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void show() {
//        mainMenu.display();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0f, 0f, 0f, 1f);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void pause() {
        // Called when the game is paused
    }

    @Override
    public void resume() {
        // Called when the game is resumed
    }

    @Override
    public void hide() {
        // Called when this screen is no longer the current screen for the game
    }

    @Override
    public void dispose() {
        // Clean up resources
        stage.dispose();
        skin.dispose();
    }
}
