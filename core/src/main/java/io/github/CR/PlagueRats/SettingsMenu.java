package io.github.CR.PlagueRats;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class SettingsMenu extends MainMenu {
    private final String[] buttonNames;
    private String[] labelStrings;
    private Slider soundSlider;
    private Slider musicSlider;

    public SettingsMenu(final RatGame game) {
        buttonNames = new String[] {
            "Resolution", "Sounds Volume", "Music Volume"
        };

        TextButton resolutionButton = gFact.createTextButton(buttonNames[0], skin);
        TextButton soundsVolButton = gFact.createTextButton(buttonNames[1], skin);
        soundSlider = gFact.createSlider(skin);
        soundSlider.setVisualPercent(100);
        TextButton musicVolButton = gFact.createTextButton(buttonNames[2], skin);
        musicSlider = gFact.createSlider(skin);
        musicSlider.setVisualPercent(100);

//        labelStrings = new String[] { String.valueOf(soundSlider.getVisualPercent()), String.valueOf(musicSlider.getVisualPercent()) };
//        Label soundLabel = gFact.createLabel(labelStrings[0], skin);
//        Label musicLabel = gFact.createLabel(labelStrings[1], skin);

        stage.addActor(resolutionButton);
        stage.addActor(soundsVolButton);
        stage.addActor(soundSlider);
//        stage.addActor(soundLabel);
        stage.addActor(musicVolButton);
        stage.addActor(musicSlider);
//        stage.addActor(musicLabel);
    }


//    public void render(float delta) {
//        labelStrings = new String[] { String.valueOf(soundSlider.getVisualPercent()), String.valueOf(musicSlider.getVisualPercent()) };
//
//    }

}
