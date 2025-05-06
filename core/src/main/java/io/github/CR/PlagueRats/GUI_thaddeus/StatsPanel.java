package io.github.CR.PlagueRats.GUI_thaddeus;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.thaddycat.gradletest.backend.GameCharacter;

public class StatsPanel extends Table {
    private final Label nameLabel;
    private final Label hpLabel;
    private final Label commandLabel;


    public StatsPanel(Skin skin) {
        super();
        nameLabel = new Label("Name: N/A", skin);
        hpLabel = new Label("HP: N/A", skin);
        commandLabel = new Label("Queued: N/A", skin);

        this.top().left();
        this.add(commandLabel).left().row();
        this.add(nameLabel).left().row();
        this.add(hpLabel).left().row();
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        // add logic to hide/show actual UI elements if needed
    }

    public void updateCommandText(GameCharacter character) {
        if (character != null) {
            nameLabel.setText("Name: " + character.getName());
            hpLabel.setText("HP: " + character.getHp() + "/" + character.getMaxHp());
        } else {
            nameLabel.setText("Name: N/A");
            hpLabel.setText("HP: N/A");
        }
    }
    public void update(GameCharacter c, String queuedCommand) {
        nameLabel.setText(c==null ? "Name: N/A" : "Name: "+c.getName());
        hpLabel.  setText(c==null ? "HP: N/A"   : "HP: "+c.getHp()+"/"+c.getMaxHp());
        commandLabel.setText("Queued: "+(queuedCommand==null ? "N/A" : queuedCommand));
    }
}


