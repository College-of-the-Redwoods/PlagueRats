public class WoodResource implements Collectable {

    // Pseudocode:
    // Return a Resource with name "Wood" and amount 1
    @Override
    public Resource collect() {
        return new Resource("Wood", 1);
    }
}
