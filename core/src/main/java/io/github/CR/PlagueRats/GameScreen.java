package io.github.CR.PlagueRats;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameScreen implements Screen {

    private final OrthographicCamera camera;
    private final SpriteBatch spriteBatch;
    Texture backgroundTexture;
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
        spriteBatch = new SpriteBatch();
        backgroundTexture = new Texture("backgroundmap.jpeg");
        ratTexture = new Texture("ladyrat.png");
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
        ScreenUtils.clear(Color.BLACK);

        // Update camera
        camera.update();

        viewport.apply();

        // Draw something
        spriteBatch.begin();

        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        spriteBatch.draw(backgroundTexture, ratPosX, ratPosY, worldWidth, worldHeight);
        ratSprite.draw(spriteBatch);

        spriteBatch.end();
    }

    private void logic() {
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();
        float ratWidth = ratSprite.getWidth();
        float ratHeight = ratSprite.getHeight();

        // trying to keep the rat in the screen
        ratSprite.setX(MathUtils.clamp(ratSprite.getX(), 0, worldWidth - ratWidth));
    }

    private void input() {
        float speed = 3f;
        float delta = Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            ratSprite.translateX(speed * delta);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            ratSprite.translateX(-speed * delta);
        }
/*
        else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            ratSprite.translateY(speed * delta); //extrapolation might not work
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            ratSprite.translateY(-speed * delta); //extrapolation might not work
        }
*/
        if (Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);
            ratSprite.setCenterX(touchPos.x);
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
        spriteBatch.dispose();
    }
}
