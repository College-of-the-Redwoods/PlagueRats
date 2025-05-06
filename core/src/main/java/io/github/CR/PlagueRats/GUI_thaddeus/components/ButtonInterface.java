package io.github.CR.PlagueRats.GUI_thaddeus.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface ButtonInterface {
    void render(SpriteBatch batch);
    void update(float delta);
    boolean isClicked(int screenX, int screenY, int button);
}

