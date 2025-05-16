package io.github.CR.PlagueRats.GUI_thaddeus.control;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import io.github.CR.PlagueRats.GUI_thaddeus.input.UIManager;
import io.github.CR.PlagueRats.GUI_thaddeus.record.CommandRecord;

/**
 * GlobalKeyHandler
 * ->
 * Intercepts SPACE and BACKSPACE key presses:
 * • SPACE: clears backend queue, replays UI history, steps the game, clears previews, refreshes positions.
 * • BACKSPACE: undoes the last step, clears previews, refreshes positions.
 */
public class GlobalKeyHandler extends InputAdapter {
    private final UIManager ui;
    private final GameStage stage;

    public GlobalKeyHandler(UIManager ui, GameStage stage) {
        this.ui = ui;
        this.stage = stage;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.SPACE:
                // 1) clear backend commands
                GameController.INSTANCE.getCurrentCommands().clear();
                // 2) replay UI history into backend
                for (CommandRecord rec : ui.getHistory()) {
                    if (rec.type == CommandRecord.Type.MOVE) {
                        GameController.INSTANCE.move(rec.actor, rec.cellTarget);
                    } else {
                        GameController.INSTANCE.attack(rec.actor, rec.charTarget);
                    }
                }
                // 3) execute step
                GameController.INSTANCE.step();
                // 4) clear UI previews
                ui.clearCommandPreviews();
                // 5) refresh character positions
                stage.refreshAllCharacterPositions();
                return true;

            case Input.Keys.BACKSPACE:
                // Undo last step
                GameController.INSTANCE.undo();
                ui.clearCommandPreviews();
                stage.refreshAllCharacterPositions();
                return true;

            default:
                return false;
        }
    }
}
/*
 * Pattern: Command    ◀ Behavioral (it replays CommandRecord instances)
 * Pattern: Observer   ◀ Behavioral (listens to keyboard events)
 */
