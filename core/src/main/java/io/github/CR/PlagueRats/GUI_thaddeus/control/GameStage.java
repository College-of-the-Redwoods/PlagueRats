package io.github.CR.PlagueRats.GUI_thaddeus.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import io.github.CR.PlagueRats.GUI_thaddeus.input.CameraWrapper;
import io.github.CR.PlagueRats.GUI_thaddeus.components.StatsPanel;
import io.github.CR.PlagueRats.GUI_thaddeus.render.CharacterActor;
import io.github.CR.PlagueRats.GUI_thaddeus.render.SpriteProvider;
import io.github.CR.PlagueRats.backend.AbstractCharacter;
import io.github.CR.PlagueRats.backend.Cell;
import io.github.CR.PlagueRats.backend.Command;
import io.github.CR.PlagueRats.backend.MapGenerator;

import java.util.List;

public class GameStage extends Stage {
    private final StatsPanel stats;
    private final int cellSize;
    private final SpriteProvider spriteProvider;

    public GameStage(CameraWrapper cameraWrapper,
                     Skin skin,
                     int cellSize,
                     SpriteProvider spriteProvider)
    {
        super(new ScreenViewport(cameraWrapper.getCamera())); // calls Stage()
        this.cellSize= cellSize;
        this.spriteProvider = spriteProvider;
        this.stats = new StatsPanel(skin, () -> {});

        stats.setPosition(10, Gdx.graphics.getHeight() - 10);
        addActor(stats);

        // spawn a CharacterActor per model character
        for (AbstractCharacter c : AbstractCharacter.getCharacterArrayList()) {
            Cell start = MapGenerator.getCellAt(c.getPosition().x, c.getPosition().y);
            if (start != null && !start.isOccupied()) {
                start.occupyCell();
            }
            addActor(new CharacterActor(c, cellSize, spriteProvider));
        }
    }

    public void refreshAllCharacterPositions() {
        for (Actor a : getActors()) {
            if (a instanceof CharacterActor) {
                ((CharacterActor) a).updatePosition();
            }
        }
    }

    public void showStatsFor(AbstractCharacter c) {
        List<Command> cmds = c.getCommandManager().getCurrentCommands();
        String lastCmd = cmds.isEmpty()
            ? "N/A"
            : cmds.get(cmds.size() - 1).toString();
        stats.update(c, lastCmd);
    }
}
