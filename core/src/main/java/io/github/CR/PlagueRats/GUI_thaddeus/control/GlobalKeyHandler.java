package io.github.CR.PlagueRats.GUI_thaddeus.control;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import io.github.CR.PlagueRats.GUI_thaddeus.input.UIManager;
import io.github.CR.PlagueRats.GUI_thaddeus.record.CommandRecord;

/**
 * Handles “forward” and “undo” via keyboard.
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
                GameController.INSTANCE.getQueue().clear();
                // 2) replay UI history into backend
                for (CommandRecord rec : ui.getHistory()) {
                    if (rec.type == CommandRecord.Type.MOVE) {
                        GameController.INSTANCE.move(rec.actor, rec.cellTarget);
                    } else {
                        GameController.INSTANCE.attack(rec.actor, rec.charTarget);
                    }
                }
                // 3) execute
                GameController.INSTANCE.step();
                // 4) clear UI previews
                ui.clearCommandPreviews();
                // 5) refresh visuals
                stage.refreshAllCharacterPositions();
                return true;

            case Input.Keys.BACKSPACE:
                // implement undo similarly if desired
                GameController.INSTANCE.undo();
                ui.clearCommandPreviews();
                stage.refreshAllCharacterPositions();
                return true;

            default:
                return false;
        }
    }
}
