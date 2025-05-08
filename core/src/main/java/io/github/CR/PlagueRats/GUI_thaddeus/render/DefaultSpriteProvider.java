package io.github.CR.PlagueRats.GUI_thaddeus.render;


import io.github.CR.PlagueRats.backend.AbstractCharacter;
import io.github.CR.PlagueRats.backend.NPCCharacter;
import io.github.CR.PlagueRats.backend.PCCharacter;
/**
 * DefaultSpriteProvider
 * Strategy for mapping an AbstractCharacter to its sprite path:
 *   • PCCharacter → john.png
 *   • NPCCharacter → jane.png
 *   • fallback → ratTestSprite2.png
 */
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
            return "ratTestSprite2.png";
        }
    }
}
/*
 * Patterns:
 *   • Strategy — injectable SpriteProvider
 */
