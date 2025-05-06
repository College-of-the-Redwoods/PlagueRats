package io.github.CR.PlagueRats.GUI_thaddeus.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class GameButton implements ButtonInterface {
    protected Rectangle bounds;
    protected Texture texture;
    protected Runnable onClick;
    protected Sound clickSound;
    protected boolean isHovered;

    public GameButton(float x, float y, float width, float height, Texture texture, Runnable onClick) {
        this.bounds = new Rectangle(x, y, width, height);
        this.texture = texture;
        this.onClick = onClick;
    }

    public void setClickSound(Sound clickSound) {
        this.clickSound = clickSound;
    }

    @Override
    public void render(SpriteBatch batch) {
        if (isHovered) {
            batch.setColor(1, 1, 1, 0.75f); // Slightly transparent if hovered
        } else {
            batch.setColor(1, 1, 1, 1f);
        }
        batch.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height);
        batch.setColor(1, 1, 1, 1f); // Reset color
    }

    @Override
    public void update(float delta) {
        Vector2 mouse = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        mouse.y = Gdx.graphics.getHeight() - mouse.y; // Flip Y
        isHovered = bounds.contains(mouse);
    }

    @Override
    public boolean isClicked(int screenX, int screenY, int button) {
        if (button != Input.Buttons.LEFT) return false;

        Vector2 worldCoords = new Vector2(screenX, screenY);
        worldCoords.y = Gdx.graphics.getHeight() - worldCoords.y; // Flip Y

        if (bounds.contains(worldCoords)) {
            if (clickSound != null) {
                clickSound.play(0.25f); // Play at 25% volume
            }
            if (onClick != null) {
                onClick.run();
            }
            return true;
        }
        return false;
    }

    public void setOnClick(Runnable onClick) {
        this.onClick = onClick;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
