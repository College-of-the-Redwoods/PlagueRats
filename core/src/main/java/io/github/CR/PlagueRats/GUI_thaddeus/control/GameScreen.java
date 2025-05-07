package io.github.CR.PlagueRats.GUI_thaddeus.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import io.github.CR.PlagueRats.GUI_thaddeus.input.*;
import io.github.CR.PlagueRats.GUI_thaddeus.render.*;
import io.github.CR.PlagueRats.backend.*;

import java.util.List;

/**
 * GameScreen
 * ->
 * A LibGDX Screen that:
 * 1. Sets up camera and SpriteBatch.
 * 2. Instantiates renderers (map, characters, commands).
 * 3. Builds the Scene2D Stage & UI (GameStage + UIManager).
 * 4. Installs input processors (selection, commands, global keys).
 * 5. In render(), draws the map, commands, and stage.
 */

public class GameScreen implements Screen {

    private static final int CELL_SIZE = 40;

    private final OrthographicCamera  camera;
    private final SpriteBatch         batch;
    private final MapRenderer mapRenderer;
    private final CharacterRenderer charRenderer;
    private final CommandRenderer cmdRenderer;

    private final GameStage gameStage;
    private final UIManager uiManager;

    public GameScreen(TurnBasedGame turnBasedGame) {
        // 1) Camera setup
        camera = new OrthographicCamera(800, 500);
        camera.position.set(400, 250, 0);
        camera.update();
        CameraWrapper cameraWrapper = new CameraWrapper(camera);

        // 2) Rendering tools
        batch   = new SpriteBatch();
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        // 3) Backend state reference
        List<Cell> cells = MapGenerator.getCellArray();
        List<AbstractCharacter> characters = AbstractCharacter.getCharacterArrayList();
        SpriteProvider provider = new DefaultSpriteProvider();

        // 4) Renderers initialization
        mapRenderer  = new MapRenderer(cells, CELL_SIZE);
        cmdRenderer = new CommandRenderer(batch,
            new Texture("drop.png"),
            new Texture("sword_blue.png"),
            CELL_SIZE);
        charRenderer = new CharacterRenderer(batch, characters, CELL_SIZE, new DefaultSpriteProvider());

        // 5) Stage and UI
        this.gameStage = new GameStage(cameraWrapper, skin, CELL_SIZE, provider);
        this.uiManager = new UIManager(gameStage, skin);

        // 6) Input routing
            // A) character selector
        CharacterSelector selector  =  new CharacterSelector(cameraWrapper,
            uiManager,
            CELL_SIZE);
            // B) Command-menu
        CommandMenuOpener opener = new CommandMenuOpener(cameraWrapper,
            selector,
            uiManager,
            gameStage,
            skin,
            CELL_SIZE);
            // C) global keys handler
        GlobalKeyHandler keys = new GlobalKeyHandler(uiManager,gameStage);

        // build the input multiplexer and register in desired order:
        InputMuxBuilder builder = new InputMuxBuilder();

        // 1 + 2 → UI layer               (Stage + raw UI)
        builder.addUIProcessor(gameStage);
        builder.addUIProcessor(uiManager);      // optional, only if UIManager implements InputProcessor

        //  gameplay interactions  (selection + commands)
        // 3) selecting PCs
        builder.addGameplayProcessor(selector);

        // 4) commands
        builder.addGameplayProcessor(opener);

        // 5 → global keys & fallback
        builder.addGlobalProcessor(keys);

        // e) Finally catch-all fallback handler, if you have one
        //  builder.addGlobalProcessor(otherHandler);

        Gdx.input.setInputProcessor(builder.build());
    }
    @Override
    public void render(float delta) {
        // Clear screen
        Gdx.gl.glClearColor(0.1f,0.1f,0.3f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw map, commands, and Scene2D stage
        camera.update();
        mapRenderer.render(camera);
        cmdRenderer.render(camera, uiManager.getHistory());
        gameStage.act(delta);
        gameStage.draw();
    }

    // Boilerplate lifecycle methods
    @Override
    public void show() {
        // Prepare your screen here.
    }
    @Override
    public void resize(int width, int height) {
        // Resize your screen here. The parameters represent the new window size.
    }
    @Override
    public void pause() {
        // Invoked when your application is paused.
    }
    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }
    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }
    @Override
    public void dispose() {
        batch.dispose();
        cmdRenderer.dispose();
        mapRenderer.dispose();
    }
}
/*
 * Patterns:
 *   • Template Method     ◀ Creational  (implements Screen lifecycle: show(), render(), etc.)
 *   • Builder             ◀ Creational  (uses InputMuxBuilder to assemble input processors)
 *   • Composite           ◀ Structural (Scene2D Stage + multiple actors/trees)
 *   • Observer            ◀ Behavioral  (registers InputProcessors and ClickListeners)
 */
