package io.github.CR.PlagueRats.GUI_thaddeus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import io.github.CR.PlagueRats.backend.*;

import java.util.List;

public class GameScreen implements Screen {
    private static final int CELL_SIZE = 40;
    private final OrthographicCamera camera;
    private final SpriteBatch batch;
    private final InputRouter inputRouter;
    private final MapRenderer mapRenderer;
    private final CharacterRenderer charRenderer;
    private final CommandRenderer cmdRenderer;
    private final GameStage gameStage;

    public GameScreen(TurnBasedGame game) {
        // — camera & wrappers
        camera = new OrthographicCamera(800, 500);
        camera.position.set(400, 250, 0);
        camera.update();
        CameraWrapper cameraWrapper = new CameraWrapper(camera);
        batch = new SpriteBatch();

        // - skin,
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        // - menuManager & interactor
        MenuManager menuManager = new MenuManager();
        GameWorldInteractor interactor = new GameWorldInteractor(CharacterManager.getInstance());
        InteractionManager interactionManager = new InteractionManager(interactor);

        // - stage
        gameStage = new GameStage(cameraWrapper, menuManager, interactionManager, skin);
        Gdx.input.setInputProcessor(gameStage);

        // - grab data lists
        List<Cell> cells = MapGenerator.getCellArray();
        List<GameCharacter> characters = CharacterManager.getInstance().getCharacterArrayList();

        // — instantiate helper classes
        UIManager uiManager = new UIManager(gameStage, skin);
        Gdx.input.setInputProcessor(gameStage);
        inputRouter  = new InputRouter(gameStage,
            new InteractionManager(new GameWorldInteractor(CharacterManager.getInstance())),
            cameraWrapper
        );
        mapRenderer  = new MapRenderer(cells, CELL_SIZE);
        cmdRenderer  = new CommandRenderer(batch,
            new Texture("drop.png"),
            new Texture("sword_blue.png"),
            new Texture("sword_pink.png"),
            CELL_SIZE);

        // - sprites
        SpriteInterface spriteInterface = new DefaultSpriteProvider();
        charRenderer = new CharacterRenderer(batch, characters, CELL_SIZE, spriteInterface);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f,0.1f,0.3f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        mapRenderer.render(camera);
        charRenderer.render(camera);
        cmdRenderer.render(camera, GameController.INSTANCE.getQueue());
        gameStage.act(delta);
        gameStage.draw();

        inputRouter.update();

        gameStage.act(delta);
        gameStage.draw();
    }

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
