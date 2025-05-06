package io.github.CR.PlagueRats.GUI_thaddeus.render;

import io.github.CR.PlagueRats.backend.AbstractCharacter;

public interface SpriteProvider {
    /** Return the file‐path (relative to your assets folder) for this character. */
    String getSpritePath(AbstractCharacter character);
}
