package io.github.CR.PlagueRats.backend;

public interface Command {
    void execute();
    void undo();
    void reset();
}
