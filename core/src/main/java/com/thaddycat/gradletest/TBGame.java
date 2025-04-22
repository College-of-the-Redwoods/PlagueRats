package com.thaddycat.gradletest;

import com.badlogic.gdx.Game;

public class TBGame extends Game {

    @Override
    public void create () {
        this.setScreen(new GameScreen());
    }
}
