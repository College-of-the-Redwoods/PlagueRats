package io.github.CR.PlagueRats.GUI_toshi.DeathState;

public class ConcreteStateDeath extends State {

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

