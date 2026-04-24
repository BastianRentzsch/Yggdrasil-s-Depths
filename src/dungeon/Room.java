// Copyright (c) 2026 Bastian Rentzsch

package dungeon;

import itemSystem.Item;

import java.util.*;

// Represents a room in the dungeon, including exits, items, and visual sides
public class Room {
    private final Map<Direction, Exit> exits = new EnumMap<>( Direction.class );
    private final List<Item> items = new ArrayList<>();
    private final Map<Direction, Side> sides = new EnumMap<>( Direction.class );

    // Adds an exit from this room
    public void addExit( Exit exit ) {
        this.exits.put( exit.direction(), exit );
    }

    // Returns the exit in a given direction
    public Exit getExit( Direction direction ) {
        return this.exits.get( direction );
    }

    // Adds a visual side for a direction
    public  void addSide( Direction direction, Side side ) {
        this.sides.put( direction, side );
    }

    // Returns the side for a given direction
    public Side getSide( Direction direction ) {
        return this.sides.get( direction );
    }

    // Adds an item to the room
    public void addItem( Item item ) {
        if ( item == null ) {
            throw new IllegalArgumentException( "Cannot add null item to room." );
        }

        this.items.add( item );
    }

    // Prints all items in the room
    public void printItems() {
        if ( this.items.isEmpty() ) {
            System.out.println( "There are no items here." );
            return;
        }

        System.out.println( "You see:" );

        for ( Item item : this.items ) {
            System.out.println( "- " + item.getName() + " - " + item.getDescription() );
        }
    }

    // Removes and returns an item by name, or null if not found
    public Item takeItem( String name ) {
        for ( Item item : this.items ) {
            if ( item.getName().equalsIgnoreCase( name ) ) {
                this.items.remove( item );
                return item;
            }
        }
        return null;
    }
}