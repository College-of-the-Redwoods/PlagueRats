package io.github.CR.PlagueRats.GUI_thaddeus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import com.badlogic.gdx.scenes.scene2d.Actor;
import io.github.CR.PlagueRats.backend.AbstractCharacter;
import io.github.CR.PlagueRats.backend.Position;

/**
 * A Scene2D Actor that wraps an AbstractCharacter and draws its sprite.
 */
public class CharacterActor extends Actor {
    private final AbstractCharacter character;
    private final Texture texture;
    private final int cellSize;

    public CharacterActor(AbstractCharacter character, int cellSize, SpriteProvider spriteProvider) {
        this.character = character;
        this.cellSize = cellSize;
        String spritePath = spriteProvider.getSpritePath(character);
        this.texture = new Texture(Gdx.files.internal(spritePath));
        // set size and initial position
        setSize(cellSize, cellSize);
        // disable touch so UI clicks fall through to the selectors
        setTouchable(com.badlogic.gdx.scenes.scene2d.Touchable.disabled);
        updatePosition();
    }

    /**
     * Reposition actor based on its model's Position.
     */
    public void updatePosition() {
        Position p = character.getPosition();
        setPosition(p.x * cellSize, p.y * cellSize);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    /**
     * Dispose the texture when the stage is disposed manually (if needed).
     */
    public void dispose() {
        texture.dispose();
    }
}
