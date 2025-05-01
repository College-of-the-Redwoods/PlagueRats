package io.github.CR.PlagueRats.GUI_toshi.DeathState;

public class Context {
    private State state;
    private int health;

    public Context(State initialState, int initialHealth) {
        this.state = initialState;
        this.health = initialHealth;
    }

    public void request() {
        state.handle();
    }

    public void executeCommand(String command) {
        state.execute(command);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;

        // Check auto transition to death state
        if (health <= 0) {
            changeState(new ConcreteStateDeath(this));
        }
    }

    public void setState(State newState) {
        if (health > 0) {
            this.state = newState;
            System.out.println("State set to: " + newState.getClass().getSimpleName());
        }
    }

    public void changeState(State newState) {
        if (health <= 0) {
            this.state = newState;
            System.out.println("State changed due to health falling to zero.");
        }
    }
}
