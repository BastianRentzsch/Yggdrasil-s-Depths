package itemSystem;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private final List<Item> items = new ArrayList<>();

    public void add( Item item ) {
        items.add( item );
    }

    public void remove( Item item ) {
        items.remove( item );
    }

    public List<Item> getItems() {
        return List.copyOf( items );
    }

    public boolean contains( Item item ) {
        return items.contains( item );
    }

    public void print() {
        if ( items.isEmpty() ) {
            System.out.println( "Inventory is empty." );
            return;
        }

        for ( Item item : items ) {
            System.out.println( "- " + item.getName() + " - " + item.getDescription() );
        }
    }
}