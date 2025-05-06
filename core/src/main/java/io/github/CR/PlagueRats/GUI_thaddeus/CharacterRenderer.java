package io.github.CR.PlagueRats.GUI_thaddeus;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.CR.PlagueRats.backend.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharacterRenderer {
    private final SpriteBatch batch;
    private final List<AbstractCharacter> characters;
    private final int cellSize;
    private final SpriteInterface spriteInterface;
    private final Map<String, Texture> textures = new HashMap<>();

    public CharacterRenderer(SpriteBatch batch,
                             List<AbstractCharacter> characters,
                             int cellSize,
                             SpriteInterface spriteInterface) {
        this.batch = batch;
        this.characters = characters;
        this.cellSize = cellSize;
        this.spriteInterface = spriteInterface;
    }

    public void render(OrthographicCamera cam) {
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        for (AbstractCharacter c : characters) {
            String path = spriteInterface.getSpritePath(c);
            Texture tex = textures.computeIfAbsent(path, p -> new Texture(p));
            Position p = c.getPosition();
            Cell cell = MapGenerator.getCellAt(p.x, p.y);
            batch.draw(c.getTexture(), p.x*cellSize, p.y*cellSize, cellSize, cellSize);
        }
        batch.end();
    }
}
