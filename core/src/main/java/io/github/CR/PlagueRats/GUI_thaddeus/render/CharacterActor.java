package io.github.CR.PlagueRats.GUI_thaddeus.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import com.badlogic.gdx.scenes.scene2d.Actor;
import io.github.CR.PlagueRats.backend.AbstractCharacter;
import io.github.CR.PlagueRats.backend.Position;

/**
 * CharacterActor
 * A Scene2D Actor that wraps a single AbstractCharacter:
 *   • Loads its sprite once
 *   • Positions itself based on the character’s Position model
 *   • Draws the sprite each frame
 * Single Responsibility: visual representation of one character.
 */
public class CharacterActor extends Actor {
    private final AbstractCharacter character;
    private final Texture texture;
    private final int cellSize;

    public CharacterActor(AbstractCharacter character, int cellSize, SpriteProvider spriteProvider) {
        this.character = character;
        this.cellSize = cellSize;
        // Strategy pattern: choose sprite path via injected provider
        String spritePath = spriteProvider.getSpritePath(character);
        this.texture = new Texture(Gdx.files.internal(spritePath));
        setSize(cellSize, cellSize);
        // Let input clicks fall through to world handlers
        setTouchable(com.badlogic.gdx.scenes.scene2d.Touchable.disabled);
        updatePosition();
    }
    /**
     * Pull the model’s Position and update this Actor’s onscreen coordinates.
     */
    public void updatePosition() {
        Position p = character.getPosition();
        setPosition(p.x * cellSize, p.y * cellSize);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // Render the character sprite
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    /**
     * Clean up GPU resources if/when this Actor is manually disposed.
     */
    public void dispose() {
        texture.dispose();
    }
}
/*
 * Patterns:
 *   • Strategy  — SpriteProvider injection
 *   • Adapter   — Scene2D Actor adapts our model to a drawable
 */
