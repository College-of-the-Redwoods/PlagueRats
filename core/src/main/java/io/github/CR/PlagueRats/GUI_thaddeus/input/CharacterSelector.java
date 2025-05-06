package io.github.CR.PlagueRats.GUI_thaddeus.input;

//test

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

import io.github.CR.PlagueRats.backend.AbstractCharacter;
import io.github.CR.PlagueRats.backend.Position;

/**
 * Listens for left‐clicks on the world.
 * Only PCCharacter instances can be selected.
 */
public class CharacterSelector extends InputAdapter {
    private final int cellSize  ;
    private final CameraWrapper camera;     // to convert screen‐coords into world‐coords
    private final UIManager ui;          // to update the UI


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
        if (button != Input.Buttons.LEFT) return false; // only care about left‐clicks

        // Now convert world coordinates to grid cell indices
        Vector2 world = camera.unproject(screenX, screenY);
        int cellX = (int) (world.x / cellSize);
        int cellY = (int) (world.y / cellSize);
        // 3) Dump everything so we can inspect the math
// 1) iterate *the list* of all characters
        //compare if the position of the cell clicked matches position of characters in array list, if so return that character
        Gdx.app.log("CharacterSelector",
            String.format("click screen=[%d,%d]  → world=[%.2f,%.2f]  → cell=[%d,%d]",
                screenX, screenY, world.x, world.y, cellX, cellY));

        // find the PC at that cell (or null)
        AbstractCharacter clicked = getCharacterAt(cellX, cellY);

        if (clicked == null) {
            // nothing or NPC there → clear selection
            ui.clearSelection();
            Gdx.app.log("CharacterSelector", "no PC at that cell, clearing");
            return false;  // allow downstream handlers if you like
        }

        // we found a PC → select it in the UI
        Gdx.app.log("CharacterSelector", "selecting “" + clicked.getName() + "”");
        ui.setSelectedCharacter(clicked);
        return true;     // consumed
    }

    /// Helper: returns the Character at (x,y)
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
