package io.github.CR.PlagueRats.GUI_thaddeus.input;

//test

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

import io.github.CR.PlagueRats.backend.AbstractCharacter;
import io.github.CR.PlagueRats.backend.Position;

/**
 * CharacterSelector
 * ->
 * Listens for left‐clicks on the game grid,
 * converts to cell coordinates, finds any PCCharacter,
 * and tells UIManager to select or clear.
 */
public class CharacterSelector extends InputAdapter {
    private final CameraWrapper camera;    // screen → world conversion
    private final UIManager ui;            // to update selection UI
    private final int cellSize;            // size of one grid cell in world units


    public CharacterSelector(CameraWrapper camera,
                             UIManager ui,
                             int cellSize)
    {
        this.camera = camera;
        this.ui     = ui;
        this.cellSize = cellSize;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // Only handle left‐clicks
        if (button != Input.Buttons.LEFT) return false;

        // 1) Convert screen→world→cell
        Vector2 world = camera.unproject(screenX, screenY);
        int cellX = (int) (world.x / cellSize);
        int cellY = (int) (world.y / cellSize);

        Gdx.app.log("CharacterSelector",
            String.format("click screen=[%d,%d]  → world=[%.2f,%.2f]  → cell=[%d,%d]",
                screenX, screenY, world.x, world.y, cellX, cellY));

        // 2) Find any character at that cell
        AbstractCharacter clicked = getCharacterAt(cellX, cellY);

        if (clicked == null) {
            // nothing or NPC there → clear selection
            ui.clearSelection();
            Gdx.app.log("CharacterSelector", "no PC at that cell, clearing");
            return false;
        }

        // we found a PC → select it in the UI
        Gdx.app.log("CharacterSelector", "selecting “" + clicked.getName() + "”");
        ui.setSelectedCharacter(clicked);
        return true;     // consume event
    }
    /**
     * Search global character list for one at (x,y).
     * @return the first AbstractCharacter whose position matches, or null.
     */
    public AbstractCharacter getCharacterAt(int cellX, int cellY) {
        for (AbstractCharacter c : AbstractCharacter.getCharacterArrayList()) {
            Position p = c.getPosition();
            if (p.x == cellX && p.y == cellY) {
                return c;
            }
        }
        return null;
    }
}
/*
 * Patterns:
 *   • Iterator    ◀ Behavioral (loops over character list)
 *   • Observer    ◀ Behavioral (listens for input events)
 */
