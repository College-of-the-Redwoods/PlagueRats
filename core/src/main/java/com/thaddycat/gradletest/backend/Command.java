package com.thaddycat.gradletest.backend;

public interface Command {
    void execute();
    void undo();
    void reset();
    Character getCharacter();
}
