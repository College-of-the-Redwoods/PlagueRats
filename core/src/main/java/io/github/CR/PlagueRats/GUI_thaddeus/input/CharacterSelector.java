package io.github.CR.PlagueRats.GUI_thaddeus.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

import io.github.CR.PlagueRats.GUI_thaddeus.CameraWrapper;
import io.github.CR.PlagueRats.backend.AbstractCharacter;
import io.github.CR.PlagueRats.backend.PCCharacter;
import io.github.CR.PlagueRats.backend.Position;

/**
 * Listens for left‐clicks on the world.
 * Only PCCharacter instances can be selected.
 */
public class CharacterSelector extends InputAdapter {
    private AbstractCharacter selected;
    private final int cellSize  ;
    private final CameraWrapper camera;     // to convert screen‐coords into world‐coords
    private final UIManager ui;          // to update the UI
    private CommandMenuOpener menuOpener;

    // Look for a character at that grid cell
   // private final AbstractCharacter characterArrayList = (AbstractCharacter) AbstractCharacter.getCharacterArrayList();


    public CharacterSelector(CameraWrapper camera,
                             UIManager ui,
                             int cellSize,
                             CommandMenuOpener menuOpener)
    {
        this.camera = camera;
        this.ui     = ui;
        this.cellSize = cellSize;
        this.menuOpener = menuOpener;
    }
    //so we can fix that circular dependency
        public void setMenuOpener(CommandMenuOpener menuOpener) {
        this.menuOpener = menuOpener;
    }

    /** Returns the PC at the given grid cell, or null if none/NPC. */
    public AbstractCharacter getCharacterAt(int cellX, int cellY) {
        for (AbstractCharacter c : AbstractCharacter.getCharacterArrayList()) {
            Position p = c.getPosition();
            if (p.x == cellX && p.y == cellY) {
                return (c instanceof PCCharacter) ? c : null;
            }
        }
        return null;
    }


    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // first thing: hide any right-click menu
        menuOpener.closeMenu();

        if (button != Input.Buttons.LEFT) return false; // only care about left‐clicks

        // Now convert world coordinates to grid cell indices
        Vector2 world = camera.unproject(screenX, screenY);
        int cellX = (int) (world.x / cellSize);
        int cellY = (int) (world.y / cellSize);
        // 3) Dump everything so we can inspect the math

        Gdx.app.log("CharacterSelector",
            String.format("click screen=[%d,%d]  → world=[%.2f,%.2f]  → cell=[%d,%d]",
                screenX, screenY, world.x, world.y, cellX, cellY));

        // 1) iterate *the list* of all characters
        AbstractCharacter clicked = null;
        for (AbstractCharacter c : AbstractCharacter.getCharacterArrayList()) {

            //compare if the position of the cell clicked matches position of characters in array list, if so return that character
            Position cPos = c.getPosition();
            if (cPos.x == cellX && cPos.y == cellY) {
                clicked = c;
                ui.setSelectedCharacter(clicked);
                break; //give the character
            }
        }
        // 2) no one there?
        if (clicked == null) {
            Gdx.app.log("CharacterSelector", "  no character at that cell");
            ui.clearSelection();
            return false; //let next handler try
        }

        // 5) Only PCs
        if (!(clicked instanceof PCCharacter)) {
            Gdx.app.log("CharacterSelector", "  hit NPC “" + clicked.getName() + "”—ignoring");
            ui.clearSelection();
            return false; //let next handler try
        }

        Gdx.app.log("CharacterSelector", "  selecting PC “" + clicked.getName() + "”");
        ui.setSelectedCharacter(clicked);
        return true;
    }

    public AbstractCharacter getCharacterAt(Vector2 coords) {
        return getCharacterAt((int)coords.x, (int)coords.y);
    }



}
