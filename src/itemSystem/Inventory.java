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

    public Item getItem( String itemName ) {
        for ( Item item : items ) {
            if ( item.getName().equalsIgnoreCase( itemName ) ) {
                return item;
            }
        }
        return null;
    }

    public boolean contains( Item item ) {
        return items.contains( item );
    }

    public boolean contains( String itemName ) {
        return getItem( itemName ) != null;
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