package io.github.CR.PlagueRats.GUI_thaddeus;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.CR.PlagueRats.backend.*;

import java.util.List;


//the command dont know the target get from the input class(front end)


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

    public void render(OrthographicCamera cam, List<Command> queue) {
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        for (Command cmd : queue) {
            if (cmd instanceof MoveCommand m) {
                MoveCommand m = (MoveCommand) cmd;
                Cell targetCell = m.getTarget();
                if (targetCell != null) {
                    batch.draw(
                        moveIcon,
                        targetCell.getPosition().x * cellSize,
                        targetCell.getPosition().y * cellSize,
                        cellSize, cellSize
                    );
                }
            } else if (cmd instanceof AttackCommand) {
                AttackCommand a = (AttackCommand) cmd;
                AbstractCharacter defender = a.getTarget();
                if (defender != null) {
                    Cell cell = MapGenerator.getCellAt(
                        defender.getPosition().getX(),
                        defender.getPosition().getY()
                    );
                    if (cell != null) {
                        batch.draw(
                            attackIcon,
                            cell.getPosX() * cellSize,
                            cell.getYPos() * cellSize,
                            cellSize, cellSize
                        );
                    }
                }
            }
        }
        batch.end();
    }

    public void dispose() {
        moveIcon.dispose();
        attackIcon.dispose();
    }
}
