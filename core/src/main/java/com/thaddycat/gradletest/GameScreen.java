package com.thaddycat.gradletest;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.thaddycat.gradletest.backend.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.thaddycat.gradletest.backend.Character;

import static sun.jvm.hotspot.oops.MethodData.cellSize;

public class GameScreen implements Screen {
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;
    private List<Cell> cellArray;
    private static final int CELL_SIZE = 40;
    private List<Character> characters; // Your backend characters
    private SpriteBatch batch;
    private Stage stage;
    private Skin skin;
    private TurnBasedGame game; // Replace with whatever class handles executeStep()
    private UIManager uiManager;
    private CommandMenu activeMenu;
    private Texture dropBlue;
    private Texture swordBlue;
    private Texture swordPink;
    private Texture dropTexture;


    public GameScreen() {
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 500); // match your launcher window
        game = new TurnBasedGame(); // Init your logic backend here
        characters = CharacterManager.loadCharacters();
        uiManager = new UIManager(game, characters);
        stage = uiManager.getStage();
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        batch = new SpriteBatch();
        dropBlue = new Texture(Gdx.files.internal("drop.png"));
        swordBlue = new Texture(Gdx.files.internal("sword_blue.png"));
        swordPink = new Texture(Gdx.files.internal("sword_pink.png"));
        dropTexture = new Texture(Gdx.files.internal("drop.png"));


        Gdx.input.setInputProcessor(stage);

        //cellArray = MapGenerator.getCellArray();
        MapGenerator.generateCellArray(5, 5); // or whatever size you want
        cellArray = MapGenerator.getCellArray();


        Gdx.app.log("GameScreen", "Loaded " + cellArray.size() + " cells.");

    }

    private void drawPlannedCommands(SpriteBatch batch) {
        for (Command command : game.getCommandQueue()) {
            if (command instanceof MoveCommand) {
                MoveCommand moveCmd = (MoveCommand) command;
                Character c = moveCmd.getCharacter();
                Cell target = moveCmd.getTarget();
                if (target != null) {
                    float x = target.getPosition().getX() * CELL_SIZE;
                    float y = target.getPosition().getY() * CELL_SIZE;

                    if (c.getName().equals("Jane Test")) {
                        batch.setColor(1f, 0f, 0f, 1f); // Deeper pink tint
                    } else {
                        batch.setColor(0.3f, 0.7f, 1f, 1f); // Softer blue
                    }

                    batch.draw(dropTexture, x, y, CELL_SIZE, CELL_SIZE);
                    batch.setColor(Color.WHITE); // Reset tint
                }
            } else if (command instanceof AttackCommand) {
                AttackCommand attackCmd = (AttackCommand) command;
                Character c = attackCmd.getAttacker();
                Character target = attackCmd.getTarget();
                if (target != null) {
                    Texture sword = c.getName().equals("John Test") ? swordBlue : swordPink;
                    float x = target.getPosition().getX() * CELL_SIZE;
                    float y = target.getPosition().getY() * CELL_SIZE;
                    batch.setColor(Color.WHITE);
                    batch.draw(sword, x, y, CELL_SIZE, CELL_SIZE);
                }
            }
        }
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.3f, 1); // dark blue background
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);

        // Draw each cell
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (Cell cell : cellArray) {
            int x = cell.getXPos() * CELL_SIZE;
            int y = cell.getYPos() * CELL_SIZE;

            // Use color depending on some status or default gray
            shapeRenderer.setColor(0.6f, 0.6f, 0.6f, 1);
            shapeRenderer.rect(x, y, CELL_SIZE, CELL_SIZE);
        }
        shapeRenderer.end();

        // Draw grid outlines
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0, 0, 0, 1); // black grid lines
        for (Cell cell : cellArray) {
            int x = cell.getXPos() * CELL_SIZE;
            int y = cell.getYPos() * CELL_SIZE;
            shapeRenderer.rect(x, y, CELL_SIZE, CELL_SIZE);
        }
        shapeRenderer.end();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for (Character c : characters) {
            Position pos = c.getPosition();
            Cell cell = MapGenerator.getCellAt(pos.getX(), pos.getY());
            int x = cell.getXPos() * CELL_SIZE;
            int y = cell.getYPos() * CELL_SIZE;
            batch.draw(c.getTexture(), x, y, CELL_SIZE, CELL_SIZE);
        }
        drawPlannedCommands(batch);
        batch.end();


        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            game.executeStep();
            for (Character c : characters) {
                uiManager.updateCommandInfo(c, "No command selected");
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE)) {
            game.undoStep();
        }
        if (Gdx.input.justTouched()) {
            int screenX = Gdx.input.getX();
            int screenY = Gdx.input.getY();

            // Convert screen to world coordinates
            Vector2 worldCoords = stage.getViewport().unproject(new Vector2(screenX, screenY));
            int clickedX = (int) (worldCoords.x / CELL_SIZE);
            int clickedY = (int) (worldCoords.y / CELL_SIZE);

            System.out.println("Clicked cell at: " + clickedX + ", " + clickedY);

            Cell clickedCell = MapGenerator.getCellAt(clickedX, clickedY);

            if (clickedCell != null && uiManager.getSelectedCharacter() != null) {
                // Show new CommandMenu at clicked cell
                if (activeMenu != null) {
                    closeActiveMenu();
                }

                activeMenu = new CommandMenu(stage, skin, worldCoords.x, worldCoords.y,
                    () -> {
                        Character selected = uiManager.getSelectedCharacter();
                        if (!clickedCell.isOccupied()) {
                            MoveCommand move = new MoveCommand(selected, clickedCell);
                            game.enqueueCommand(move);
                            Sound moveSound = Gdx.audio.newSound(Gdx.files.internal("drop.mp3"));
                            moveSound.play(0.25f);
                            uiManager.updateCommandInfo(selected, " queued: Move");
                            activeMenu.remove();
                            activeMenu = null;
                        } else {
                            System.out.println("Move failed: cell is occupied.");
                            activeMenu.remove();
                            activeMenu = null;
                        }
                    },
                    () -> {
                        Character selected = uiManager.getSelectedCharacter();

                        Character target = null;
                        for (Character c : characters) {
                            if (c.getPosition().getX() == clickedCell.getXPos() && c.getPosition().getY() == clickedCell.getYPos()) {
                                target = c;
                                break;
                            }
                        }

                        if (target != null && target != selected) {
                            AttackCommand attack = new AttackCommand(selected, target);
                            game.enqueueCommand(attack);
                            Sound attackSound = Gdx.audio.newSound(Gdx.files.internal("attack.mp3"));
                            attackSound.play(0.25f);
                            System.out.println("Attack command enqueued.");
                            uiManager.updateCommandInfo(selected, " queued: Attack " + target.getName());
                            activeMenu.remove();
                            activeMenu = null;
                        } else {
                            System.out.println("Attack failed: no target found or trying to attack self.");
                            activeMenu.remove();
                            activeMenu = null;
                        }
                    },
                    () -> {
                        // Cleanup logic when the menu is closed
                        activeMenu = null;
                    }
                );
            } else if (activeMenu != null) {
                // Click was outside the grid — dismiss the menu
                closeActiveMenu();
            }
        }

        stage.act(delta);
        stage.draw();
    }
    @Override public void show() {}

    @Override public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        stage.getViewport().update(width, height, true);

        stage.getViewport().setCamera(camera);


    }

    private void closeActiveMenu() {
        if (activeMenu != null) {
            activeMenu.remove();
            activeMenu = null;
        }
    }

    @Override public void pause() {}

    @Override public void resume() {}

    @Override public void hide() {}

    @Override public void dispose() {
        dropBlue.dispose();
        swordBlue.dispose();
        swordPink.dispose();
        shapeRenderer.dispose();
        batch.dispose();
        for (Character c : characters) {
            c.getTexture().dispose();
        }
    }
}
