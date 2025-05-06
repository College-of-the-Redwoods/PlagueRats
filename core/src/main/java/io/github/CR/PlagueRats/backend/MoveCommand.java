package io.github.CR.PlagueRats.backend;

import com.badlogic.gdx.Gdx;

public class MoveCommand implements Command {
    private final AbstractCharacter character;
    private Cell nextCell, previousCell;
    private boolean executed;

    public MoveCommand(AbstractCharacter character, Cell nextCell) {
        this.character = character;
        this.nextCell = nextCell;
        previousCell = MapGenerator.getCellAt(character.getPosition().x, character.getPosition().y);
        executed = false;
    }

    // only using to get current logic to work. Do not use this, it will be deleted.
    public Cell getNextCell() {
        return nextCell;
    }

    @Override
    public void execute() {
        if (!executed && !nextCell.isOccupied()) {
            if (previousCell != null) { previousCell.leaveCell(); }
            nextCell.occupyCell();
            character.setPosition(nextCell.getPosition().x, nextCell.getPosition().y);
            executed = true;
            Gdx.audio.newSound(Gdx.files.internal("drop.mp3")).play();
        }
    }

    @Override
    public void undo() {
        if (executed) {
            nextCell.leaveCell();

            if (previousCell != null) {
                previousCell.occupyCell();
                character.setPosition(previousCell.getPosition().x, previousCell.getPosition().y);
            }
            executed = false;
        }
    }

    @Override
    public void reset() {
        nextCell = null;
        previousCell = null;
        executed = false;
    }
}
