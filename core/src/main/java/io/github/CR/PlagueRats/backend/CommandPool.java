package io.github.CR.PlagueRats.backend;

import java.util.Stack;

public class CommandPool<C extends Command> {
    private final Stack<C> pool;
    private final CommandFactory<C> factory;
    private final int maxSize;

    public CommandPool(CommandFactory<C> factory, int initialSize, int maxSize) {
        this.factory = factory;
        this.maxSize = maxSize;
        pool = new Stack<>();
        for (int i = 0; i < initialSize; i++) {
            pool.push(factory.create());
        }
    }

    public C acquire() {
        return pool.isEmpty() ? factory.create() : pool.pop();
    }

    public void release(C command) {
        if (pool.size() < maxSize) {
            command.reset();
            pool.push(command);
        }
    }

    public int size() {
        return pool.size();
    }

    public interface CommandFactory<C extends Command> {
        C create();
    }
}
