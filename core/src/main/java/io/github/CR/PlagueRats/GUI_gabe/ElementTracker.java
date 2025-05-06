package io.github.CR.PlagueRats.GUI_gabe;

public class ElementTracker {
    private int elementCount;
    private int buttonID;

    public void addElement() {
        elementCount++;
    }

    public int getElementCount() {
        return elementCount;
    }
}
