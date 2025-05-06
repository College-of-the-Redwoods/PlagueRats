package io.github.CR.PlagueRats.GUI_gabe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.*;

public class GUIFactory {
    ElementTracker e = new ElementTracker();
    private int buttonID;

    public TextButton createTextButton(String name, Skin skin) {
        int bCount = e.getElementCount();
        TextButton b = new TextButton(name, skin);
        int buttonWidth = 100;
        int buttonHeight = 50;
        b.setSize(buttonWidth, buttonHeight);
        b.setPosition((float) (Gdx.graphics.getWidth() / 2 - buttonWidth / 2), (float) Gdx.graphics.getHeight() / 2 - (25 + (buttonHeight * bCount)));
        e.addElement();
        return b;
    }

    public Slider createSlider(Skin skin) {
        int bCount = e.getElementCount();
        Slider s = new Slider(0, 100, 2, false, skin);
        int sliderWidth = 200;
        int sliderHeight = 50;
        s.setSize(sliderWidth, sliderHeight);
        s.setPosition((float) (Gdx.graphics.getWidth() / 2 - sliderWidth / 2), (float) Gdx.graphics.getHeight() / 2 - (25 + (sliderHeight * bCount)));
        e.addElement();
        return s;
    }

    public Label createLabel(String text, Skin skin) {
        int bCount = e.getElementCount();
        Label l = new Label(text, skin);
        int labelWidth = 100;
        int labelHeight = 25;
        l.setSize(labelWidth, labelHeight);
        l.setPosition((float) (Gdx.graphics.getWidth() / 2 - labelWidth / 2), (float) Gdx.graphics.getHeight() / 2 - (25 + (labelHeight * bCount)));
        e.addElement();
        return l;
    }
}
