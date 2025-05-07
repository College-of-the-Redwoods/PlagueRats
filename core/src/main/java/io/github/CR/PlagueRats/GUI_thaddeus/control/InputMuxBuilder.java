package io.github.CR.PlagueRats.GUI_thaddeus.control;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;

/**
 * InputMuxBuilder
 * ->
 * Fluent builder for layering multiple InputProcessors:
 * • UI processors (Scene2D stage, UIManager)
 * • Gameplay processors (selector, command opener)
 * • Global processors (keyboard shortcuts)
 */
public class InputMuxBuilder {
    private final InputMultiplexer multiplexer;

    public InputMuxBuilder() {

        this.multiplexer = new InputMultiplexer();
    }

    /** Add a UI‐level InputProcessor. */
    public InputMuxBuilder addUIProcessor(InputProcessor processor) {
        multiplexer.addProcessor(processor);
        return this;
    }

    /** Add a gameplay‐level InputProcessor. */
    public InputMuxBuilder addGameplayProcessor(InputProcessor processor) {
        multiplexer.addProcessor(processor);
        return this;
    }

    /** Add a global‐level InputProcessor (fallback). */
    public InputMuxBuilder addGlobalProcessor(InputProcessor processor) {
        multiplexer.addProcessor(processor);
        return this;
    }

    /** Finalize and return the composed InputMultiplexer. */
    public InputMultiplexer build() {
        return multiplexer;
    }
}
 /*
  * Patterns:
  *   • Builder             ◀ Creational (fluent API to assemble an InputMultiplexer)
 */
