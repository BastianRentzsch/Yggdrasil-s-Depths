package itemSystem;

import entitySystem.Entity;

// Base class for all items in the game
public abstract class Item {
    protected String name;
    protected String description;

    public Item( String name, String description ) {
        this.name = name;
        this.description = description;
    }

    // Defines how the item is used on a target entity
    public abstract void use( Entity target );

    // Returns the item's name
    public String getName() {
        return name;
    }

    // Returns the item's description
    public String getDescription() {
        return description;
    }
}