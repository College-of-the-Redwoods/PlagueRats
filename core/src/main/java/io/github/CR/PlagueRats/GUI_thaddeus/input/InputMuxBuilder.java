package io.github.CR.PlagueRats.GUI_thaddeus.input;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;

public class InputMuxBuilder {
    private final InputMultiplexer multiplexer;

    public InputMuxBuilder() {
        this.multiplexer = new InputMultiplexer();
    }
    public InputMuxBuilder addUIProcessor(InputProcessor processor) {
        multiplexer.addProcessor(processor);
        return this;
    }

    public InputMuxBuilder addGameplayProcessor(InputProcessor processor) {
        multiplexer.addProcessor(processor);
        return this;
    }

    public InputMuxBuilder addGlobalProcessor(InputProcessor processor) {
        multiplexer.addProcessor(processor);
        return this;
    }

    public InputMultiplexer build() {
        return multiplexer;
    }
}
