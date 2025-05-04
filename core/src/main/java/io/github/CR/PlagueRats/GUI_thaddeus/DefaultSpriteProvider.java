package io.github.CR.PlagueRats.GUI_thaddeus;


import io.github.CR.PlagueRats.backend.AbstractCharacter;
import io.github.CR.PlagueRats.backend.NPCCharacter;
import io.github.CR.PlagueRats.backend.PCCharacter;

public class DefaultSpriteProvider implements SpriteProvider {
    @Override
    public String getSpritePath(AbstractCharacter character) {
        // if you ever store a custom sprite path on the character, use that,


        // // otherwise fall back to a type-based defaults:
        if (character instanceof PCCharacter) {
            return "john.png";
        }
        if (character instanceof NPCCharacter) {
            return "jane.png";
        }else {
            return "error.png";
        }
    }
}
