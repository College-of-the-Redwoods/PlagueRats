package io.github.CR.PlagueRats.GUI_thaddeus.input;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

import io.github.CR.PlagueRats.GUI_thaddeus.CameraWrapper;

import io.github.CR.PlagueRats.backend.AbstractCharacter;
import io.github.CR.PlagueRats.backend.PCCharacter;
import io.github.CR.PlagueRats.backend.Cell;
import io.github.CR.PlagueRats.backend.Position;

/**
 * Listens for left‐clicks on the world.
 * Only PCCharacter instances can be selected.
 */
public class CharacterSelector extends InputAdapter {
    private final int cellSize  ;
    private final CameraWrapper camera;     // to convert screen‐coords into world‐coords
    private final UIManager ui;          // to update the UI
    private final AbstractCharacter model;     // to ask “who’s at this spot?”
    private final CommandMenuOpener menuOpener;
    private final AbstractCharacter characterArrayList;


    public CharacterSelector(CameraWrapper camera,
                             AbstractCharacter model,
                             UIManager ui,
                             int cellSize,
                             CommandMenuOpener menuOpener)
    {
        this.camera = camera;
        this.model  = model;
        this.ui     = ui;
        this.cellSize = cellSize;
        this.menuOpener = menuOpener;
    }
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        menuOpener.closeMenu(); // first thing: hide any right-click menu
        if (button != Input.Buttons.LEFT) return false; // only care about left‐clicks
        Vector2 world = camera.unproject(screenX, screenY);
        // Now convert world coordinates to grid cell indices
        int cellX = (int) (world.x / cellSize);
        int cellY = (int) (world.y / cellSize);
        // 3) Dump everything so we can inspect the math

        Gdx.app.log("CharacterSelector",
            String.format("click screen=[%d,%d]  → world=[%.2f,%.2f]  → cell=[%d,%d]",
                screenX, screenY, world.x, world.y, cellX, cellY));

        // Look for a character at that grid cell
        characterArrayList = (AbstractCharacter) AbstractCharacter.getCharacterArrayList();
        AbstractCharacter clicked;
        for (AbstractCharacter c : characterArrayList ) {

            //compare if the position of the cell clicked matches position of characters in array list, if so return that character

            Position cPos = AbstractCharacter.getPosition();
            Position cellPos = Cell.getPosition();
            if (cPos == cellPos)
                return c; //give the character

        }

        // 4) Ask your model
        if (clicked == null) {
            Gdx.app.log("CharacterSelector", "  no character at that cell");
            model.clearSelection();
            ui.clearSelection();
            return false; //let next handler try
        }

        // 5) Only PCs
        if (!(clicked instanceof PCCharacter)) {
            Gdx.app.log("CharacterSelector", "  hit NPC “" + clicked.getName() + "”—ignoring");
            model.clearSelection();
            ui.clearSelection();
            return false; //let next handler try
        }

        Gdx.app.log("CharacterSelector", "  selecting PC “" + clicked.getName() + "”");
        model.selectCharacter(clicked);
        ui.setSelectedCharacter(clicked);
        return true;
    }
}
