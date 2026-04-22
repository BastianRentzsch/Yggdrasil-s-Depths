package dungeon;

import itemSystem.Item;

import java.util.*;

// Represents a room in the dungeon, including exits, items, and visual sides
public class Room {
    private final Map<Direction, Exit> exits = new EnumMap<>( Direction.class );
    private final List<Item> items = new ArrayList<>();
    private final Map<Direction, Side> sides = new EnumMap<>(Direction.class);

    // Adds an exit from this room
    public void addExit( Exit exit ) {
        exits.put( exit.direction(), exit );
    }

    // Returns the exit in a given direction
    public Exit getExit( Direction direction ) {
        return exits.get( direction );
    }

    // Adds a visual side for a direction
    public  void addSide( Direction direction, Side side ) {
        sides.put( direction, side);
    }

    // Returns the side for a given direction
    public Side getSide( Direction direction ) {
        return sides.get( direction );
    }

    // Adds an item to the room
    public void addItem( Item item ) {
        items.add( item );
    }

    // Prints all items in the room
    public void printItems() {
        if ( items.isEmpty() ) {
            System.out.println( "There are no items here." );
            return;
        }

        System.out.println( "You see:" );

        for ( Item item : items ) {
            System.out.println( "- " + item.getName() + " - " + item.getDescription() );
        }
    }

    // Removes and returns an item by name, or null if not found
    public Item takeItem( String name ) {
        for ( Item item : items ) {
            if ( item.getName().equalsIgnoreCase( name ) ) {
                items.remove( item );
                return item;
            }
        }
        return null;
    }
}