package io.github.CR.PlagueRats.GUI_thaddeus;

// Handles UIWindow instances, auto-hides on show

import java.util.ArrayList;
import java.util.List;

public class MenuManager {
    private UIWindow current;
    private final List<UIWindow> windows = new ArrayList<>();

    public void registerWindow(UIWindow window) {
        windows.add(window);
    }

    public void showMenu(UIWindow menu) {
        if (current != null) current.hide();
        current = menu;
        current.show();
    }
    public void closeMenu() {
        if (current != null) current.hide();
        current = null;
    }
}

