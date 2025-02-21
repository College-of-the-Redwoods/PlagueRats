package io.github.CR.PlagueRats;

import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class RatGame extends Game {
    @Override
    public void create() {
        setScreen(new FirstMenu(this));
    }
}
