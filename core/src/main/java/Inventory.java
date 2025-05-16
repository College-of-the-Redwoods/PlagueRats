import java.util.List;

public class Inventory {
    List<Resource> items;

    // Pseudocode:
    // Add new resource to inventory.
    // If resource of same type exists, increase amount.
    public void add(Resource resource) {
        // Loop through items
        // If item.name equals resource.name, add resource.amount to existing
        // Else, add as new item
    }
}
