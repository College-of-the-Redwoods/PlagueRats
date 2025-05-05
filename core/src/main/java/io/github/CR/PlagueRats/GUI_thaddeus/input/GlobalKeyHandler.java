package io.github.CR.PlagueRats.GUI_thaddeus.input;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import io.github.CR.PlagueRats.GUI_thaddeus.GameController;
import io.github.CR.PlagueRats.GUI_thaddeus.GameStage;

/**
 * Handles “forward” and “undo” via keyboard.
 */
public class GlobalKeyHandler extends InputAdapter {
    private final UIManager ui;
    private final GameStage stage;
    private final int cellSize;

        public GlobalKeyHandler(UIManager ui, GameStage stage, int cellSize) {
            this.ui       = ui;
            this.stage    = stage;
            this.cellSize = cellSize;
        }

        @Override
    public boolean keyDown(int keycode) {
            switch (keycode) {
                    case Input.Keys.SPACE:
                            GameController.INSTANCE.step();
                            ui.clearCommandPreviews();
                            stage.refreshAllCharacterPositions(cellSize);
                            return true;
                    case Input.Keys.BACKSPACE:
                            GameController.INSTANCE.undo();
                            ui.clearCommandPreviews();
                            stage.refreshAllCharacterPositions(cellSize);
                            return true;
                    default:
                            return false;
                }
            }
 }
