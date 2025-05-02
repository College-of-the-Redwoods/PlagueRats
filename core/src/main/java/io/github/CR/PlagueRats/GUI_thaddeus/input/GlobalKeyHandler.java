package io.github.CR.PlagueRats.GUI_thaddeus.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import io.github.CR.PlagueRats.GUI_thaddeus.GameController;

/**
 * Handles “forward” and “undo” via keyboard.
 */
public class GlobalKeyHandler extends InputAdapter {
    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.SPACE:
                GameController.INSTANCE.step();
                return true;
            case Input.Keys.BACKSPACE:
                GameController.INSTANCE.undo();
                return true;
            default:
                return false;
        }
    }
}
