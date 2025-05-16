public class Player extends Combatant {
    Inventory inventory;

    // Pseudocode:
    // collect() takes a Collectable resource (either wood or coin pile)
    // 1. Calls the collect() method on the resource
    // 2. Adds the returned Resource object to the inventory
    public void collect(Collectable resource) {
        Resource collected = resource.collect();
        inventory.add(collected);
    }


    public Player(String name, int health, int attackPower) {
        super(name, health, attackPower);
    }

}
