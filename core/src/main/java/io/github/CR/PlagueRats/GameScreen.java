package io.github.CR.PlagueRats;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {

    private final OrthographicCamera camera;
    private final SpriteBatch batch;
    Texture ratTexture;
    FitViewport viewport;
    float ratPosY;
    float ratPosX;
    Sprite ratSprite;
    Vector2 touchPos;


    public GameScreen(final RatGame game) {

        // Create camera and SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        ratTexture = new Texture("ratTestSprite.png");
        viewport = new FitViewport(8, 5);
        ratPosX = ((float) Gdx.graphics.getWidth() / 2) - 200;
        ratPosY = ((float) Gdx.graphics.getHeight() / 2) - 200;
        ratSprite = new Sprite(ratTexture);
        touchPos = new Vector2();

    }

    @Override
    public void show() {
        // Called when this screen becomes the current screen for the game
    }

    @Override
    public void render(float delta) {
        input();
        logic();
        draw();
    }

    private void draw() {
        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update camera
         camera.update();

//        viewport.apply();

        batch.setProjectionMatrix(camera.combined);

        // Draw something (e.g., a simple rectangle)
        batch.begin();

        ratSprite.draw(batch);

        batch.end();
    }

    private void logic() {

    }

    private void input() {
        float speed = 3f;
        float delta = Gdx.graphics.getDeltaTime();
        int key;

//        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
//            key =
//        }
//
//        switch (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
//            case 1:
//                break;
//
//        }

        if (Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
        }
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        viewport.update(width, height, true);
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
        batch.dispose();
    }
}
