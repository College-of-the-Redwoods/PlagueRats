package com.thaddycat.gradletest.backend;

import java.util.ArrayDeque;
import java.util.Deque;

public class CommandHistory {
    private final Deque<CommandMacro> history = new ArrayDeque<>();

    public void push(CommandMacro macro) {
        history.push(macro);
    }

    public CommandMacro pop() {
        return history.pop();
    }

    public void removeOldest() {
        history.removeLast();
    }

    public int size() {
        return history.size();
    }

    public boolean isEmpty() {
        return history.isEmpty();
    }
}
