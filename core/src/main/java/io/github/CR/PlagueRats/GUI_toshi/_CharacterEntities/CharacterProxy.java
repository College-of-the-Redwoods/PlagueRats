package io.github.CR.PlagueRats.GUI_toshi._CharacterEntities;

public class CharacterProxy implements ICharacter {
    private int charId;
    private Character realCharacter;

    public CharacterProxy(int charId) {
        this.charId = charId;
    }

    private void initCharacter() {
        if (realCharacter == null) {
            realCharacter = new Character(charId);
        }
    }

    @Override
    public String getName() {
        initCharacter();
        return realCharacter.getName();
    }

    @Override
    public String getStats() {
        initCharacter();
        return realCharacter.getStats();
    }

    @Override
    public void performAction(String action) {
        initCharacter();
        realCharacter.performAction(action);
    }
}
