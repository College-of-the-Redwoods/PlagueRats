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

/**
 * GameStage
 * ->
 * A Scene2D Stage that:
 * 1• Holds CharacterActor instances for each AbstractCharacter.
 * 2• Marks each starting cell occupied in the backend.
 * 3• Hosts the StatsPanel UI in the corner.
 * 4• Provides a method to refresh all actor positions.
 */

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

        // 1) StatsPanel in upper-left
        this.stats = new StatsPanel(skin, () -> {});
        stats.setPosition(10, Gdx.graphics.getHeight() - 10);
        addActor(stats);

        // 2) Spawn each character actor & occupy its cell
        for (AbstractCharacter c : AbstractCharacter.getCharacterArrayList()) {
            Cell start = MapGenerator.getCellAt(c.getPosition().x, c.getPosition().y);
            if (start != null && !start.isOccupied()) {
                start.occupyCell();
            }
            addActor(new CharacterActor(c, cellSize, spriteProvider));
        }
    }

    /** Updates every CharacterActor’s position from its model. */
    public void refreshAllCharacterPositions() {
        for (Actor a : getActors()) {
            if (a instanceof CharacterActor) {
                ((CharacterActor) a).updatePosition();
            }
        }
    }

    /** (Optional) Directly update the stats panel for a given character. */
    public void showStatsFor(AbstractCharacter c) {
        List<Command> cmds = c.getCommandManager().getCurrentCommands();
        String lastCmd = cmds.isEmpty()
            ? "N/A"
            : cmds.get(cmds.size() - 1).toString();
        stats.update(c, lastCmd);
    }
}
 /*
     * Patterns:
    *   • Composite           ◀ Structural (Stage is a Group of CharacterActor & StatsPanel)
    *   • Iterator            ◀ Behavioral  (loops over getActors() or character list)
    */
