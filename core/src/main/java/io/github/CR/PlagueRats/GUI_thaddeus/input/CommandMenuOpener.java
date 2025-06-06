package io.github.CR.PlagueRats.GUI_thaddeus.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import io.github.CR.PlagueRats.GUI_thaddeus.record.CommandRecord;
import io.github.CR.PlagueRats.GUI_thaddeus.components.CommandMenu;
import io.github.CR.PlagueRats.GUI_thaddeus.control.GameStage;
import io.github.CR.PlagueRats.backend.*;

/**
 * CommandMenuOpener
 * ->
 * On right‐click, if a PC is selected and has no queued action:
 *   • computes target cell/character
 *   • opens a small CommandMenu at mouse location
 *   • records move or attack choice in UIManager
 *   • clamps menu to stay on-screen
 */
//  Single Responsibility: only responsible for “showing the menu”
public class CommandMenuOpener extends InputAdapter {
    private final CameraWrapper camera;
    private final CharacterSelector model;
    private final UIManager ui;
    private final GameStage stage;
    private final Skin skin;
    private final int cellSize;
    private CommandMenu currentMenu;

    public CommandMenuOpener(CameraWrapper camera,
                             CharacterSelector model,
                             UIManager ui,
                             GameStage stage,
                             Skin skin,
                             int cellSize) {
        this.camera   = camera;
        this.model    = model;
        this.ui = ui;
        this.stage    = stage;
        this.skin     = skin;
        this.cellSize = cellSize;
    }

    /** Remove existing menu if present */
    public void closeMenu() {
        if (currentMenu != null) {
            Gdx.app.log("CmdMenuOpener", "closeMenu() removing existing menu");
            currentMenu.remove();
            currentMenu = null;
        }
    }

    @Override
    public boolean touchDown(int sx, int sy, int ptr, int button) {
        Gdx.app.log("CmdMenuOpener",
            String.format("touchDown sx=%d sy=%d ptr=%d btn=%d", sx, sy, ptr, button));

        // Only right‐click
        if (button != Input.Buttons.RIGHT) {
            Gdx.app.log("CmdMenuOpener", "  → not right-click, ignoring");
            return false;
        }
        // **Tear down the old popup** before we build a new one:
        closeMenu();

        // 1) PC must be selected
        AbstractCharacter sel = ui.getSelectedCharacter();
        Gdx.app.log("CmdMenuOpener", "  selected character = " + (sel != null ? sel.getName() : "null"));
        if (sel == null) {
            Gdx.app.log("CmdMenuOpener", "  → no character selected, abort");
            return false;
        }
        // 2) PC cannot already have a queued action
        boolean alreadyHasCmd =
            ui.getHistory().stream().anyMatch(r -> r.actor == sel);
        if (alreadyHasCmd) {
            Gdx.app.log("CmdMenuOpener", sel.getName() + " already has a queued action");
            return true;
        }

        // 3) Convert to cell coords
        Vector2 world = camera.unproject(sx, sy);
        int cellX = (int)(world.x / cellSize);
        int cellY = (int)(world.y / cellSize);
        Gdx.app.log("CmdMenuOpener",
            String.format("  world=[%.2f,%.2f] → cell=[%d,%d]", world.x, world.y, cellX, cellY));

        Cell cell        = MapGenerator.getCellAt(cellX, cellY);
        AbstractCharacter rawTarget = model.getCharacterAt(cellX, cellY);
         // don’t let sel attack itself:
        final AbstractCharacter target = (rawTarget != null && rawTarget != sel)
            ? rawTarget
            : null;

        // 4) Build and show CommandMenu
        currentMenu = new CommandMenu(
            stage, skin, world.x, world.y,

            // onMove
            () -> {
                if (cell == null) {
                    Gdx.app.log("CmdMenuOpener", "Off‐map: abort");
                } else {
                    // prevent moving onto any occupied cell
                    boolean occupiedByChar = AbstractCharacter
                        .getCharacterArrayList()
                        .stream()
                        .anyMatch(ch -> {
                            Position p = ch.getPosition();
                            return p.x == cell.getPosition().x && p.y == cell.getPosition().y;
                        });
                    if (occupiedByChar) {
                        Gdx.app.log("CmdMenuOpener", "Move target occupied: abort");
                    } else {
                        ui.record(CommandRecord.move(sel, cell));
                    }
                }
            },

            // onAttack:
            () -> {
                if (target == null) return;
                ui.record(CommandRecord.attack(sel, target));
            },

            // onClose:
            () -> {
                currentMenu.remove();
                currentMenu = null;
            }
        );
        Gdx.app.log("CmdMenuOpener", "  CommandMenu should now be visible");

        // Force layout so we can get size
        currentMenu.pack();

        // Clamp position so menu stays within screen
        float menuX = Math.min(world.x, stage.getWidth() - currentMenu.getWidth());
        float menuY = Math.min(world.y, stage.getHeight() - currentMenu.getHeight());

        currentMenu.setPosition(menuX, menuY);

        // Add it to the stage
        stage.addActor(currentMenu);
        return true;
    }
}
/*
 * Patterns:
 *   • Observer    ◀ Behavioral (listens for right‐click)
 *   • Command     ◀ Behavioral (records user commands)
 *   • Composite   ◀ Structural (uses CommandMenu Table)
 */
