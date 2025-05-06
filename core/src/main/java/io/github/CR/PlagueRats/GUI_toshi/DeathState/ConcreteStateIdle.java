package io.github.CR.PlagueRats.GUI_toshi.DeathState;

public class ConcreteStateIdle extends State {

    public ConcreteStateIdle(Context context) {
        super(context);
    }

    @Override
    public void handle() {
        System.out.println("Character is idle and ready.");
    }

    public void setState(State newState) {
        if (context.getHealth() > 0) {
            context.setState(newState);
            System.out.println("Transitioning to new state.");
        } else {
            System.out.println("Health too low to change state.");
        }
    }

    @Override
    public void execute(String command) {
        System.out.println("Executing command: " + command);
        // Add logic here for actual command handling if needed
    }
}

