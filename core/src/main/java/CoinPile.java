public class CoinPile implements Collectable {

    // Pseudocode:
    // Return a Resource with name "Coins" and amount 10 (or random)
    @Override
    public Resource collect() {
        return new Resource("Coins", 10);
    }
}
