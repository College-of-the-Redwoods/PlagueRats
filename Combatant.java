public class Combatant {
    protected String name;
    protected int health;
    protected int attackPower;

    public Combatant(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }


    public void attack(Combatant target) {
        System.out.println(name + " attacks " + target.name + " for " + attackPower + " damage.");
        target.takeDamage(attackPower);
    }


    public void takeDamage(int amount) {
        health -= amount;
        if (health < 0) {
            health = 0;
        }
        System.out.println(name + " takes " + amount + " damage. Remaining health: " + health);
        if (!isAlive()) {
            System.out.println(name + " has been defeated!");
        }
    }


    public boolean isAlive() {
        return health > 0;
    }
    public int getHealth() {
        return health;
    }
    public String getName() {
        return name;
    }

}
