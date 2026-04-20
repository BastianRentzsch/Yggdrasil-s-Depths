package dungeon;

import itemSystem.Item;

import java.util.*;

public class Room {
    private final Map<Direction, Exit> exits = new EnumMap<>( Direction.class );
    private final List<Item> items = new ArrayList<>();

    public void addExit( Exit exit ) {
        exits.put( exit.getDirection(), exit );
    }

    public Exit getExit( Direction direction ) {
        return exits.get( direction );
    }

    public void addItem( Item item ) {
        items.add( item );
    }

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

    public Item takeItem( String name ) {
        for ( Item item : items ) {
            if ( item.getName().equalsIgnoreCase( name ) ) {
                items.remove( item );
                return item;
            }
        }
        return null;
    }


    public String toString() {
        return Objects.toString( exits );
    }
}
