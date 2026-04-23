package itemSystem;

import java.util.ArrayList;
import java.util.List;

// Represents the player's inventory and manages stored items
public class Inventory {
    private final List<Item> items = new ArrayList<>();

    // Adds an item to the inventory
    public void add( Item item ) {
        this.items.add( item );
    }

    // Removes an item from the inventory
    public void remove( Item item ) {
        this.items.remove( item );
    }

    // Returns a copy of all items in the inventory
    public List<Item> getItems() {
        return List.copyOf( this.items );
    }

    // Finds an item by name (case-insensitive)
    public Item getItem( String itemName ) {
        for ( Item item : this.items ) {
            if ( item.getName().equalsIgnoreCase( itemName ) ) {
                return item;
            }
        }
        return null;
    }

    // Prints all items in the inventory
    public void print() {
        if ( this.items.isEmpty() ) {
            System.out.println( "Inventory is empty." );
            return;
        }

        for ( Item item : this.items ) {
            System.out.println( "- " + item.getName() + " - " + item.getDescription() );
        }
    }
}