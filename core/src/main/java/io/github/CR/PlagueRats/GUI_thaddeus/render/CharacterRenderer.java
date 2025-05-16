package io.github.CR.PlagueRats.GUI_thaddeus.render;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.CR.PlagueRats.backend.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * CharacterRenderer
 * Draws all characters each frame using a single SpriteBatch:
 *   • Caches textures in a Map<String,Texture>
 *   • Queries each character’s Position
 *   • Renders at (x*cellSize,y*cellSize)
 * Single Responsibility: batch‐rendering of all characters.
 */
public class CharacterRenderer {
    private final SpriteBatch batch;
    private final List<AbstractCharacter> characters;
    private final int cellSize;
    private final SpriteProvider spriteInterface;
    private final Map<String, Texture> textures = new HashMap<>();

    public CharacterRenderer(SpriteBatch batch,
                             List<AbstractCharacter> characters,
                             int cellSize,
                             SpriteProvider spriteInterface) {
        this.batch = batch;
        this.characters = characters;
        this.cellSize = cellSize;
        this.spriteInterface = spriteInterface;
    }
    /**
     * Renders each character’s sprite at its model position.
     */
    public void render(OrthographicCamera cam) {
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        for (AbstractCharacter c : characters) {
            String path = spriteInterface.getSpritePath(c);
            Texture tex = textures.computeIfAbsent(path,Texture::new);
            Position p = c.getPosition();
            batch.draw(tex, p.x*cellSize, p.y*cellSize, cellSize, cellSize);
        }
        batch.end();
    }
}
/*
 * Patterns:
 *   • Flyweight — caching Texture instances keyed by path
 */
