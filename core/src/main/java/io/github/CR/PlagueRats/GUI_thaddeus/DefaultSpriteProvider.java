package io.github.CR.PlagueRats.GUI_thaddeus;

import com.thaddycat.gradletest.backend.GameCharacter;
import com.thaddycat.gradletest.backend.NPCCharacter;
import com.thaddycat.gradletest.backend.PCCharacter;

public class DefaultSpriteProvider implements SpriteInterface {
    @Override
    public String getSpritePath(GameCharacter character) {
        // if you ever store a custom sprite path on the character, use that,

        if (character.getSpritePath() != null && !character.getSpritePath().isEmpty()) {
            return character.getSpritePath();          // e.g. "sprites/bob.png"
        }
        // // otherwise fall back to a type-based defaults:
        if (character instanceof PCCharacter) {
            return "john.png";
        }
        if (character instanceof NPCCharacter) {
            return "jahn.png";
        }else {
            return "error.png";
        }
    }
}
