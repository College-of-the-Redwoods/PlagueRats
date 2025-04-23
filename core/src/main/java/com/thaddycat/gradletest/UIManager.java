package com.thaddycat.gradletest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.thaddycat.gradletest.backend.TurnBasedGame;
import com.thaddycat.gradletest.backend.Character;

import java.util.List;

public class UIManager {
    private Stage stage;
    private Character selectedCharacter;
    private Label commandInfoLabel;
    private Table rootTable;
    private Label johnCommandLabel;
    private Label janeCommandLabel;




    public UIManager(TurnBasedGame game, List<Character> characters) {
        stage = new Stage();
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        rootTable = new Table();
        rootTable.top().left();
        rootTable.setFillParent(true);

        TextButton stepButton = new TextButton("Next Turn (spacebar)", skin);
        TextButton undoButton = new TextButton("Undo (backspace)", skin);

        TextButton selectJohn = new TextButton("Select John", skin);
        TextButton selectJane = new TextButton("Select Jane", skin);

        commandInfoLabel = new Label("No command selected", skin);
        johnCommandLabel = new Label("No command", skin);
        janeCommandLabel = new Label("No command", skin);

        rootTable.add(commandInfoLabel).colspan(2).padTop(10).left();

        stage.addActor(rootTable);


        selectJohn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                for (Character c : characters) {
                    if (c.getName().equals("John Test")) {
                        selectedCharacter = c;
                        updateCommandInfoLabels();
                        System.out.println("Selected character: John Test");
                    }
                }
            }
        });

        selectJane.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                for (Character c : characters) {
                    if (c.getName().equals("Jane Test")) {
                        selectedCharacter = c;
                        updateCommandInfoLabels();
                        System.out.println("Selected character: Jane Test");
                    }
                }
            }
        });


        stepButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.executeStep();
            }
        });

        undoButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.undoStep();
            }
        });

        Table table = new Table();
        table.bottom().left();
        table.setPosition(220, 100); // X right of grid, Y mid-grid height
        table.add(stepButton).pad(10);
        table.add(undoButton).pad(10);
        table.setDebug(true); // this will draw borders around table cells
        table.row();
        table.add(selectJohn).pad(10);
        table.add(selectJane).pad(10);
        table.row();
        table.add(johnCommandLabel).padBottom(10); // Puts it under John's button
        table.add(janeCommandLabel).padBottom(10); // Puts it under Jane's button

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
        Gdx.app.log("UIManager", "Step and Undo buttons created and added to stage.");

    }

    public Stage getStage() {
        return stage;
    }

    public Character getSelectedCharacter() {
        return selectedCharacter;
    }

    public void updateCommandInfo(Character character, String info) {
        if (character == null) return;

        if (character.getName().equals("John Test")) {
            johnCommandLabel.setText(info);
        } else if (character.getName().equals("Jane Test")) {
            janeCommandLabel.setText(info);
        }
    }

    private void updateCommandInfoLabels() {
        if (selectedCharacter != null) {
            // Optionally, re-show their command info here if you're storing it
        }
    }
}
