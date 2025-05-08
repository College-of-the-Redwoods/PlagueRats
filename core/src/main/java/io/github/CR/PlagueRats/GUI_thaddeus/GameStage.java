package io.github.CR.PlagueRats.GUI_thaddeus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.thaddycat.gradletest.backend.GameCharacter;

public class GameStage extends Stage {
    private final CameraWrapper      camera;
    private final InteractionManager interactor;
    private final MenuManager        menus;
    private final StatsPanel         stats;

    public GameStage(CameraWrapper cameraWrapper,
                     MenuManager menus,
                     InteractionManager interactor,
                     Skin skin)
     {
        super(new ScreenViewport(cameraWrapper.getCamera())); // calls Stage()
        this.camera = cameraWrapper;
        this.menus = menus;
        this.interactor = interactor;
        this.stats = new StatsPanel(skin);
        stats.setPosition(10, Gdx.graphics.getHeight() - 10);
        addActor(stats);
    }

    public void showStatsFor(GameCharacter c) {
        stats.update(c, GameController.INSTANCE.getQueue().stream()
            .filter(cmd->cmd.getCharacter()==c)
            .reduce((a,b)->b).map(Object::toString).orElse("N/A"));
    }
}
