package io.github.CR.PlagueRats.GUI_thaddeus.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import io.github.CR.PlagueRats.GUI_thaddeus.CameraWrapper;
import io.github.CR.PlagueRats.GUI_thaddeus.CommandMenu;
import io.github.CR.PlagueRats.GUI_thaddeus.GameController;
import io.github.CR.PlagueRats.GUI_thaddeus.GameStage;
import io.github.CR.PlagueRats.backend.*;

//  Single Responsibility: only responsible for “showing the menu”
public class CommandMenuOpener extends InputAdapter {
    private final CameraWrapper camera;
    private final CharacterManager model;
    private final GameStage stage;
    private final Skin skin;
    private final int cellSize;

    private CommandMenu currentMenu;

    public CommandMenuOpener(CameraWrapper camera,
                             CharacterManager model,
                             UIManager ui,
                             GameStage stage,
                             Skin skin,
                             int cellSize) {


        this.camera   = camera;
        this.model    = model;
        this.stage    = stage;
        this.skin     = skin;
        this.cellSize = cellSize;
    }
    public void closeMenu() {
        if (currentMenu != null) {
            currentMenu.remove();
            currentMenu = null;
        }
    }

    @Override
    public boolean touchDown(int sx, int sy, int ptr, int button) {
        if (button != Input.Buttons.RIGHT) return false;

        // **Tear down the old popup** before we build a new one:
        if (currentMenu != null) {
            currentMenu.remove();
            currentMenu = null;
        }

        // 1) only if a PC is selected
        AbstractCharacter sel = model.getSelectedCharacter();
        if (sel == null) return false;

        // 2) translate screen → world → cell
        Vector2 world = camera.unproject(sx, sy);
        int cellX = (int)(world.x / cellSize);
        int cellY = (int)(world.y / cellSize);

        Cell cell        = MapGenerator.getCellAt(cellX, cellY);
        AbstractCharacter target = model.getCharacterAt(new Vector2(cellX, cellY));   // null if empty

        // 2) build the menu and capture it so we can remove it later
        // 1-slot box so our lambdas can close over it:
        // onMove:
        // onAttack:
        // onClose:

        currentMenu = new CommandMenu(
            stage, skin, world.x, world.y,
            // onMove:
            () -> {
                if (cell == null) {
                    Gdx.app.log("CmdMenu","Off-map");
                } else if (cell.isOccupied()) {
                    Gdx.app.log("CmdMenu","Invalid move: occupied");
                } else {
                    GameController.INSTANCE.step(new MoveCommand(sel,cell));
                }
            },
            // onAttack:
            () -> {
                if (target == null || target == sel) {
                    Gdx.app.log("CmdMenu","Invalid attack");
                } else {
                    GameController.INSTANCE.step(new AttackCommand(sel,target));
                }
            },
            // onClose:
            () -> {
                currentMenu.remove();
                currentMenu = null;
            }
        );
        return true;
    }
}
