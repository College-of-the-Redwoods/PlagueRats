package io.github.CR.PlagueRats.GUI_thaddeus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class InputRouter extends InputAdapter {
    private final GameStage   uiStage;
    private final InteractionManager worldInteractor;
    private final CameraWrapper cameraWrapper;

    public InputRouter(GameStage uiStage, InteractionManager interactor, CameraWrapper cameraWrapper) {
        this.uiStage         = uiStage;
        this.worldInteractor = interactor;
        this.cameraWrapper    = cameraWrapper;
    }

    public void update() {
        // Keyboard
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))    GameController.INSTANCE.step();
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE)) GameController.INSTANCE.undo();

        // Touch/click
        if (Gdx.input.justTouched()) {
            int mx = Gdx.input.getX(), my = Gdx.input.getY();
            // if any UI actor hit → UIStage handles it
            if (uiStage.hit(mx, my, true) != null) {
                uiStage.touchDown(mx, my, 0, 0);
            } else {
                Vector3 world3 = cameraWrapper.getCamera().unproject(new Vector3(mx, my, 0));
                Vector2 world2 = new Vector2(world3.x, world3.y);
                worldInteractor.routeClick(world2);
            }
        }
    }
}
