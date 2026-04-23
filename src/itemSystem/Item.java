package itemSystem;

// Base class for all items in the game
public class Item {
    protected final String name;
    protected final String description;

    public Item( String name, String description ) {
        this.name = name;
        this.description = description;
    }

    // Returns the item's name
    public String getName() {
        return this.name;
    }

    // Returns the item's description
    public String getDescription() {
        return this.description;
    }
}