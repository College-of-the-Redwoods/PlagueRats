package io.github.CR.PlagueRats.GUI_thaddeus.render;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.CR.PlagueRats.GUI_thaddeus.record.CommandRecord;

import java.util.List;

/**
 * CommandRenderer
 * Draws an icon on each queued CommandRecord’s target cell:
 *   • Uses moveIcon for MOVE commands
 *   • Uses attackIcon for ATTACK commands
 *   • Draws at cellTarget or charTarget positions
 * Single Responsibility: visual feedback of queued actions.
 */
public class CommandRenderer {
    private final SpriteBatch batch;
    private final Texture moveIcon;
    private final Texture attackIcon;
    private final int cellSize;

    public CommandRenderer(SpriteBatch batch,
                           Texture moveIcon,
                           Texture attackIcon,
                           int cellSize) {
        this.batch       = batch;
        this.moveIcon    = moveIcon;
        this.attackIcon  = attackIcon;
        this.cellSize    = cellSize;
    }
    /**
     * Renders each command’s icon at the target location.
     */
    public void render(OrthographicCamera cam, List<CommandRecord> records) {
        batch.setProjectionMatrix(cam.combined);
        batch.begin();

        for (CommandRecord rec : records) {
            Texture icon = rec.type == CommandRecord.Type.MOVE
                ? moveIcon
                : attackIcon;
            int x = rec.type == CommandRecord.Type.MOVE
                ? rec.cellTarget.getPosition().x
                : rec.charTarget.getPosition().x;
            int y = rec.type == CommandRecord.Type.MOVE
                ? rec.cellTarget.getPosition().y
                : rec.charTarget.getPosition().y;

            batch.draw(icon, x * cellSize, y * cellSize, cellSize, cellSize);
        }
        batch.end();
    }
    public void dispose() {
        moveIcon.dispose();
        attackIcon.dispose();
    }
}
/*
 * Patterns:
 *   • Interpreter — examines CommandRecord.Type to choose icon
 */
