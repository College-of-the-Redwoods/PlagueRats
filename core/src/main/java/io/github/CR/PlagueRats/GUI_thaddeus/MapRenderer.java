package io.github.CR.PlagueRats.GUI_thaddeus;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.github.CR.PlagueRats.backend.Cell;
import io.github.CR.PlagueRats.backend.Position;

import java.util.List;

public class MapRenderer {
    private final ShapeRenderer shapes;
    private final List<Cell> cells;
    private final int cellSize;

    public MapRenderer(List<Cell> cells, int cellSize) {
        this.shapes   = new ShapeRenderer();
        this.cells    = cells;
        this.cellSize = cellSize;
    }

    public void render(OrthographicCamera cam) {
        shapes.setProjectionMatrix(cam.combined);
        shapes.begin(ShapeRenderer.ShapeType.Filled);
        shapes.setColor(0.6f,0.6f,0.6f,1);
        for (Cell cell : cells) {
            Position cPos = cell.getPosition();
            shapes.rect(cPos.x*cellSize, cPos.y*cellSize, cellSize, cellSize);
        }
        shapes.end();

        shapes.begin(ShapeRenderer.ShapeType.Line);
        shapes.setColor(0,0,0,1);
        for (Cell cell : cells) {
            Position cPos = cell.getPosition();
            shapes.rect(cPos.x*cellSize, cPos.y*cellSize, cellSize, cellSize);
        }
        shapes.end();
    }

    public void dispose() {
        shapes.dispose();
    }
}
