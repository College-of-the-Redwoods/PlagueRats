package io.github.CR.PlagueRats.GUI_thaddeus;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.thaddycat.gradletest.backend.Cell;

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
        for (Cell cell : cells) {
            shapes.setColor(0.6f,0.6f,0.6f,1);
            shapes.rect(cell.getXPos()*cellSize, cell.getYPos()*cellSize, cellSize, cellSize);
        }
        shapes.end();

        shapes.begin(ShapeRenderer.ShapeType.Line);
        shapes.setColor(0,0,0,1);
        for (Cell cell : cells) {
            shapes.rect(cell.getXPos()*cellSize, cell.getYPos()*cellSize, cellSize, cellSize);
        }
        shapes.end();
    }

    public void dispose() {
        shapes.dispose();
    }
}
