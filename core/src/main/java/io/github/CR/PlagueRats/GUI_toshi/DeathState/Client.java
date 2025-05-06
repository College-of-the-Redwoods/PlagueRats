package io.github.CR.PlagueRats.GUI_toshi.DeathState;

public class Client {
    public static void main(String[] args) {
        Context context = new Context(null, 100); // Starting with 100 health
        State idle = new ConcreteStateIdle(context);
        context.setState(idle);

        context.request();
        context.executeCommand("build");

        System.out.println("\nSimulating damage...");
        context.setHealth(0);  // Triggers auto transition to death state

        context.request();  // Should now be death state
        context.executeCommand("attack");
    }
}
