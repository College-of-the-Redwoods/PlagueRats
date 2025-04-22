public class Combatant {
    String name;
    int health;
    int attackPower;

    // Pseudocode:
    // Attack another combatant
    // 1. Print attack message
    // 2. Call target.takeDamage(attackPower)
    public void attack(Combatant target) {
        System.out.println(name + " attacks " + target.name + " for " + attackPower + " damage.");
        target.takeDamage(attackPower);
    }

    // Pseudocode:
    // Reduce this combatant’s health by amount
    // 1. Subtract amount from health
    // 2. Print new health
    public void takeDamage(int amount) {
        health -= amount;
        System.out.println(name + " takes " + amount + " damage. Remaining health: " + health);
    }

    // Pseudocode:
    // Return true if health > 0
    public boolean isAlive() {
        return health > 0;
    }

}
