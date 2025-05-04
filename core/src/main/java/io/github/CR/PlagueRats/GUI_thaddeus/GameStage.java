package io.github.CR.PlagueRats.GUI_thaddeus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import io.github.CR.PlagueRats.GUI_thaddeus.input.MenuManager;
import io.github.CR.PlagueRats.backend.AbstractCharacter;
import io.github.CR.PlagueRats.backend.Command;

import java.util.List;

public class GameStage extends Stage {
    private final StatsPanel         stats;

    public GameStage(CameraWrapper cameraWrapper,
                     MenuManager menus,
                     Skin skin)
    {
        super(new ScreenViewport(cameraWrapper.getCamera())); // calls Stage()
        this.stats = new StatsPanel(skin);
        stats.setPosition(10, Gdx.graphics.getHeight() - 10);
        addActor(stats);
    }

    public void showStatsFor(AbstractCharacter c) {
        List<Command> cmds = c.getCommandManager().getCurrentCommands();
        String lastCmd = cmds.isEmpty()
            ? "N/A"
            : cmds.get(cmds.size() - 1).toString();
        stats.update(c, lastCmd);
    }
}
