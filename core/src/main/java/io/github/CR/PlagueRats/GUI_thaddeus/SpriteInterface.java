package io.github.CR.PlagueRats.GUI_thaddeus;

import com.thaddycat.gradletest.backend.GameCharacter;

public interface SpriteInterface {
    /** Return the file‐path (relative to your assets folder) for this character. */
    String getSpritePath(GameCharacter character);
}
