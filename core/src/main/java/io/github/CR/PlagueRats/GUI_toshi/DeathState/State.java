package io.github.CR.PlagueRats.GUI_toshi.DeathState;

public abstract class State {
    protected Context context;

    public State(Context context) {
        this.context = context;
    }

    public abstract void handle();

    public int getHealth() {
        return context.getHealth();  // Now uses Context's health
    }

    public abstract void execute(String command);

    public static class ConcreteStateDeath extends State {

        public ConcreteStateDeath(Context context) {
            super(context);
        }

        @Override
        public void handle() {
            System.out.println("Character is dead. No actions possible.");
        }

        @Override
        public void execute(String command) {
            System.out.println("Cannot execute '" + command + "'. Character is dead.");
        }
    }
}
