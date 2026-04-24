// Copyright (c) 2026 Bastian Rentzsch

package dungeon;

import java.util.*;

import static dungeon.SidesCodex.sidesCodex;
import static itemSystem.ItemCodex.itemCodex;

// Responsible for building and initializing the full dungeon layout
public class Dungeon {
    // Creates all rooms, connects them, assigns visuals (sides), and places items
    public static List<Room> createDungeon() {
        List<Room> rooms = new ArrayList<>();

        // Create 36 rooms
        for ( int i = 0; i < 36; i++ ) {
            rooms.add( new Room() );
        }

        // Add structure to the dungeon
        addExits( rooms );
        addSides( rooms );
        addItems( rooms );

        return rooms;
    }

    // Defines how rooms are connected via exits (north, south, east, west)
    static private void addExits( List<Room> rooms ) {
        // First Row
        rooms.get( 0 ).addExit( new Exit( Direction.EAST, rooms.get( 1 ) ) );
        rooms.get( 0 ).addExit( new Exit( Direction.SOUTH, rooms.get( 6 ) ) );

        rooms.get( 1 ).addExit( new Exit( Direction.EAST, rooms.get( 2 ) ) );
        rooms.get( 1 ).addExit( new Exit( Direction.SOUTH, rooms.get( 7 ) ) );
        rooms.get( 1 ).addExit( new Exit( Direction.WEST, rooms.get( 0 ) ) );

        rooms.get( 2 ).addExit( new Exit( Direction.EAST, rooms.get( 3 ) ) );
        rooms.get( 2 ).addExit( new Exit( Direction.WEST, rooms.get( 1 ) ) );

        rooms.get( 3 ).addExit( new Exit( Direction.WEST, rooms.get( 2 ) ) );

        rooms.get( 4 ).addExit( new Exit( Direction.EAST, rooms.get( 5 ) ) );

        rooms.get( 5 ).addExit( new Exit( Direction.SOUTH, rooms.get( 11 ) ) );
        rooms.get( 5 ).addExit( new Exit( Direction.WEST, rooms.get( 4 ) ) );

        // Second Row
        rooms.get( 6 ).addExit( new Exit( Direction.NORTH, rooms.get( 0 ) ) );
        rooms.get( 6 ).addExit( new Exit( Direction.SOUTH, rooms.get( 12 ) ) );

        rooms.get( 7 ).addExit( new Exit( Direction.NORTH, rooms.get( 1 ) ) );

        rooms.get( 8 ).addExit( new Exit( Direction.EAST, rooms.get( 9 ) ) );
        rooms.get( 8 ).addExit( new Exit( Direction.SOUTH, rooms.get( 14 ) ) );

        rooms.get( 9 ).addExit( new Exit( Direction.EAST, rooms.get( 10 ) ) );
        rooms.get( 9 ).addExit( new Exit( Direction.SOUTH, rooms.get( 15 ) ) );
        rooms.get( 9 ).addExit( new Exit( Direction.WEST, rooms.get( 8 ) ) );

        rooms.get( 10 ).addExit( new Exit( Direction.EAST, rooms.get( 11 ) ) );
        rooms.get( 10 ).addExit( new Exit( Direction.WEST, rooms.get( 9 ) ) );

        rooms.get( 11 ).addExit( new Exit( Direction.NORTH, rooms.get( 5 ) ) );
        rooms.get( 11 ).addExit( new Exit( Direction.SOUTH, rooms.get( 17 ) ) );
        rooms.get( 11 ).addExit( new Exit( Direction.WEST, rooms.get( 10 ) ) );

        // Third Row
        rooms.get( 12 ).addExit( new Exit( Direction.NORTH, rooms.get( 6 ) ) );
        rooms.get( 12 ).addExit( new Exit( Direction.SOUTH, rooms.get( 18 ) ) );

        rooms.get( 13 ).addExit( new Exit( Direction.EAST, rooms.get( 14 ) ) );
        rooms.get( 13 ).addExit( new Exit( Direction.SOUTH, rooms.get( 19 ) ) );

        rooms.get( 14 ).addExit( new Exit( Direction.NORTH, rooms.get( 8 ) ) );
        rooms.get( 14 ).addExit( new Exit( Direction.WEST, rooms.get( 13 ) ) );

        rooms.get( 15 ).addExit( new Exit( Direction.NORTH, rooms.get( 9 ) ) );
        rooms.get( 15 ).addExit( new Exit( Direction.EAST, rooms.get( 16 ) ) );

        rooms.get( 16 ).addExit( new Exit( Direction.SOUTH, rooms.get( 22 ) ) );
        rooms.get( 16 ).addExit( new Exit( Direction.WEST, rooms.get( 15 ) ) );

        rooms.get( 17 ).addExit( new Exit( Direction.NORTH, rooms.get( 11 ) ) );
        rooms.get( 17 ).addExit( new Exit( Direction.SOUTH, rooms.get( 23 ) ) );

        // Fourth Row
        rooms.get( 18 ).addExit( new Exit( Direction.NORTH, rooms.get( 12 ) ) );
        rooms.get( 18 ).addExit( new Exit( Direction.EAST, rooms.get( 19 ) ) );
        rooms.get( 18 ).addExit( new Exit( Direction.SOUTH, rooms.get( 24 ) ) );

        rooms.get( 19 ).addExit( new Exit( Direction.NORTH, rooms.get( 13 ) ) );
        rooms.get( 19 ).addExit( new Exit( Direction.EAST, rooms.get( 20 ) ) );
        rooms.get( 19 ).addExit( new Exit( Direction.WEST, rooms.get( 18 ) ) );

        rooms.get( 20 ).addExit( new Exit( Direction.EAST, rooms.get( 21 ) ) );
        rooms.get( 20 ).addExit( new Exit( Direction.WEST, rooms.get( 19 ) ) );

        rooms.get( 21 ).addExit( new Exit( Direction.SOUTH, rooms.get( 27 ) ) );
        rooms.get( 21 ).addExit( new Exit( Direction.WEST, rooms.get( 20 ) ) );

        rooms.get( 22 ).addExit( new Exit( Direction.NORTH, rooms.get( 16 ) ) );

        rooms.get( 23 ).addExit( new Exit( Direction.NORTH, rooms.get( 17 ) ) );
        rooms.get( 23 ).addExit( new Exit( Direction.SOUTH, rooms.get( 29 ) ) );

        // Fifth Row
        rooms.get( 24 ).addExit( new Exit( Direction.NORTH, rooms.get( 18 ) ) );

        rooms.get( 25 ).addExit( new Exit( Direction.EAST, rooms.get( 26 ) ) );
        rooms.get( 25 ).addExit( new Exit( Direction.SOUTH, rooms.get( 31 ) ) );

        rooms.get( 26 ).addExit( new Exit( Direction.WEST, rooms.get( 25 ) ) );

        rooms.get( 27 ).addExit( new Exit( Direction.NORTH, rooms.get( 21 ) ) );
        rooms.get( 27 ).addExit( new Exit( Direction.SOUTH, rooms.get( 33 ) ) );

        rooms.get( 28 ).addExit( new Exit( Direction.EAST, rooms.get( 29 ) ) );
        rooms.get( 28 ).addExit( new Exit( Direction.SOUTH, rooms.get( 34 ) ) );

        rooms.get( 29 ).addExit( new Exit( Direction.NORTH, rooms.get( 23 ) ) );
        rooms.get( 29 ).addExit( new Exit( Direction.WEST, rooms.get( 28 ) ) );

        // Sixth Row
        rooms.get( 30 ).addExit( new Exit( Direction.EAST, rooms.get( 31 ) ) );

        rooms.get( 31 ).addExit( new Exit( Direction.NORTH, rooms.get( 25 ) ) );
        rooms.get( 31 ).addExit( new Exit( Direction.EAST, rooms.get( 32 ) ) );
        rooms.get( 31 ).addExit( new Exit( Direction.WEST, rooms.get( 30 ) ) );

        rooms.get( 32 ).addExit( new Exit( Direction.EAST, rooms.get( 33 ) ) );
        rooms.get( 32 ).addExit( new Exit( Direction.WEST, rooms.get( 31 ) ) );

        rooms.get( 33 ).addExit( new Exit( Direction.NORTH, rooms.get( 27 ) ) );
        rooms.get( 33 ).addExit( new Exit( Direction.WEST, rooms.get( 32 ) ) );

        rooms.get( 34 ).addExit( new Exit( Direction.NORTH, rooms.get( 28 ) ) );
        rooms.get( 34 ).addExit( new Exit( Direction.EAST, rooms.get( 35 ) ) );

        rooms.get( 35 ).addExit( new Exit( Direction.WEST, rooms.get( 34 ) ) );
    }

    // Assigns ASCII-art visual representations to each room side
    static private void addSides( List<Room> rooms ) {
        // First Row
        rooms.get( 0 ).addSide(Direction.NORTH, sidesCodex.get( 0 ) );
        rooms.get( 0 ).addSide(Direction.EAST, sidesCodex.get( 1 ) );
        rooms.get( 0 ).addSide(Direction.SOUTH, sidesCodex.get( 2 ) );
        rooms.get( 0 ).addSide(Direction.WEST, sidesCodex.get( 3 ) );

        rooms.get( 1 ).addSide(Direction.NORTH, sidesCodex.get( 4 ) );
        rooms.get( 1 ).addSide(Direction.EAST, sidesCodex.get( 5 ) );
        rooms.get( 1 ).addSide(Direction.SOUTH, sidesCodex.get( 6 ) );
        rooms.get( 1 ).addSide(Direction.WEST, sidesCodex.get( 7 ) );

        rooms.get( 2 ).addSide(Direction.NORTH, sidesCodex.get( 4 ) );
        rooms.get( 2 ).addSide(Direction.EAST, sidesCodex.get( 6 ) );
        rooms.get( 2 ).addSide(Direction.SOUTH, sidesCodex.get( 43 ) );
        rooms.get( 2 ).addSide(Direction.WEST, sidesCodex.get( 28 ) );

        rooms.get( 3 ).addSide(Direction.NORTH, sidesCodex.get( 3 ) );
        rooms.get( 3 ).addSide(Direction.EAST, sidesCodex.get( 9 ) );
        rooms.get( 3 ).addSide(Direction.SOUTH, sidesCodex.get( 0 ) );
        rooms.get( 3 ).addSide(Direction.WEST, sidesCodex.get( 8 ) );

        rooms.get( 4 ).addSide(Direction.NORTH, sidesCodex.get( 10 ) );
        rooms.get( 4 ).addSide(Direction.EAST, sidesCodex.get( 11 ) );
        rooms.get( 4 ).addSide(Direction.SOUTH, sidesCodex.get( 3 ) );
        rooms.get( 4 ).addSide(Direction.WEST, sidesCodex.get( 9 ) );

        rooms.get( 5 ).addSide(Direction.NORTH, sidesCodex.get( 3 ) );
        rooms.get( 5 ).addSide(Direction.EAST, sidesCodex.get( 10 ) );
        rooms.get( 5 ).addSide(Direction.SOUTH, sidesCodex.get( 12 ) );
        rooms.get( 5 ).addSide(Direction.WEST, sidesCodex.get( 6 ) );

        // Second Row
        rooms.get( 6 ).addSide(Direction.NORTH, sidesCodex.get( 11 ) );
        rooms.get( 6 ).addSide(Direction.EAST, sidesCodex.get( 36 ) );
        rooms.get( 6 ).addSide(Direction.SOUTH, sidesCodex.get( 13 ) );
        rooms.get( 6 ).addSide(Direction.WEST, sidesCodex.get( 4 ) );

        rooms.get( 7 ).addSide(Direction.NORTH, sidesCodex.get( 14 ) );
        rooms.get( 7 ).addSide(Direction.EAST, sidesCodex.get( 15 ) );
        rooms.get( 7 ).addSide(Direction.SOUTH, sidesCodex.get( 9 ) );
        rooms.get( 7 ).addSide(Direction.WEST, sidesCodex.get( 16 ) );

        rooms.get( 8 ).addSide(Direction.NORTH, sidesCodex.get( 10 ) );
        rooms.get( 8 ).addSide(Direction.EAST, sidesCodex.get( 17 ) );
        rooms.get( 8 ).addSide(Direction.SOUTH, sidesCodex.get( 42 ) );
        rooms.get( 8 ).addSide(Direction.WEST, sidesCodex.get( 15 ) );

        rooms.get( 9 ).addSide(Direction.NORTH, sidesCodex.get( 4 ) );
        rooms.get( 9 ).addSide(Direction.EAST, sidesCodex.get( 19 ) );
        rooms.get( 9 ).addSide(Direction.SOUTH, sidesCodex.get( 20 ) );
        rooms.get( 9 ).addSide(Direction.WEST, sidesCodex.get( 20 ) );

        rooms.get( 10 ).addSide(Direction.NORTH, sidesCodex.get( 4 ) );
        rooms.get( 10 ).addSide(Direction.EAST, sidesCodex.get( 14 ) );
        rooms.get( 10 ).addSide(Direction.SOUTH, sidesCodex.get( 4 ) );
        rooms.get( 10 ).addSide(Direction.WEST, sidesCodex.get( 21 ) );

        rooms.get( 11 ).addSide(Direction.NORTH, sidesCodex.get( 7 ) );
        rooms.get( 11 ).addSide(Direction.EAST, sidesCodex.get( 4 ) );
        rooms.get( 11 ).addSide(Direction.SOUTH, sidesCodex.get( 22 ) );
        rooms.get( 11 ).addSide(Direction.WEST, sidesCodex.get( 23 ) );

        // Third Row
        rooms.get( 12 ).addSide(Direction.NORTH, sidesCodex.get( 24 ) );
        rooms.get( 12 ).addSide(Direction.EAST, sidesCodex.get( 43 ) );
        rooms.get( 12 ).addSide(Direction.SOUTH, sidesCodex.get( 25 ) );
        rooms.get( 12 ).addSide(Direction.WEST, sidesCodex.get( 4 ) );

        rooms.get( 13 ).addSide(Direction.NORTH, sidesCodex.get( 16 ) );
        rooms.get( 13 ).addSide(Direction.EAST, sidesCodex.get( 20 ) );
        rooms.get( 13 ).addSide(Direction.SOUTH, sidesCodex.get( 14 ) );
        rooms.get( 13 ).addSide(Direction.WEST, sidesCodex.get( 15 ) );

        rooms.get( 14 ).addSide(Direction.NORTH, sidesCodex.get( 18 ) );
        rooms.get( 14 ).addSide(Direction.EAST, sidesCodex.get( 15 ) );
        rooms.get( 14 ).addSide(Direction.SOUTH, sidesCodex.get( 26 ) );
        rooms.get( 14 ).addSide(Direction.WEST, sidesCodex.get( 20 ) );

        rooms.get( 15 ).addSide(Direction.NORTH, sidesCodex.get( 14 ) );
        rooms.get( 15 ).addSide(Direction.EAST, sidesCodex.get( 18 ) );
        rooms.get( 15 ).addSide(Direction.SOUTH, sidesCodex.get( 15 ) );
        rooms.get( 15 ).addSide(Direction.WEST, sidesCodex.get( 26 ) );

        rooms.get( 16 ).addSide(Direction.NORTH, sidesCodex.get( 15 ) );
        rooms.get( 16 ).addSide(Direction.EAST, sidesCodex.get( 10 ) );
        rooms.get( 16 ).addSide(Direction.SOUTH, sidesCodex.get( 6 ) );
        rooms.get( 16 ).addSide(Direction.WEST, sidesCodex.get( 27 ) );

        rooms.get( 17 ).addSide(Direction.NORTH, sidesCodex.get( 28 ) );
        rooms.get( 17 ).addSide(Direction.EAST, sidesCodex.get( 4 ) );
        rooms.get( 17 ).addSide(Direction.SOUTH, sidesCodex.get( 29 ) );
        rooms.get( 17 ).addSide(Direction.WEST, sidesCodex.get( 43 ) );

        // Fourth Row
        rooms.get( 18 ).addSide(Direction.NORTH, sidesCodex.get( 30 ) );
        rooms.get( 18 ).addSide(Direction.EAST, sidesCodex.get( 31 ) );
        rooms.get( 18 ).addSide(Direction.SOUTH, sidesCodex.get( 6 ) );
        rooms.get( 18 ).addSide(Direction.WEST, sidesCodex.get( 4 ) );

        rooms.get( 19 ).addSide(Direction.NORTH, sidesCodex.get( 27 ) );
        rooms.get( 19 ).addSide(Direction.EAST, sidesCodex.get( 32 ) );
        rooms.get( 19 ).addSide(Direction.SOUTH, sidesCodex.get( 43 ) );
        rooms.get( 19 ).addSide(Direction.WEST, sidesCodex.get( 14 ) );

        rooms.get( 20 ).addSide(Direction.NORTH, sidesCodex.get( 36 ) );
        rooms.get( 20 ).addSide(Direction.EAST, sidesCodex.get( 18 ) );
        rooms.get( 20 ).addSide(Direction.SOUTH, sidesCodex.get( 36 ) );
        rooms.get( 20 ).addSide(Direction.WEST, sidesCodex.get( 33 ) );

        rooms.get( 21 ).addSide(Direction.NORTH, sidesCodex.get( 3 ) );
        rooms.get( 21 ).addSide(Direction.EAST, sidesCodex.get( 10 ) );
        rooms.get( 21 ).addSide(Direction.SOUTH, sidesCodex.get( 32 ) );
        rooms.get( 21 ).addSide(Direction.WEST, sidesCodex.get( 34 ) );

        rooms.get( 22 ).addSide(Direction.NORTH, sidesCodex.get( 20 ) );
        rooms.get( 22 ).addSide(Direction.EAST, sidesCodex.get( 3 ) );
        rooms.get( 22 ).addSide(Direction.SOUTH, sidesCodex.get( 9 ) );
        rooms.get( 22 ).addSide(Direction.WEST, sidesCodex.get( 35 ) );

        rooms.get( 23 ).addSide(Direction.NORTH, sidesCodex.get( 8 ) );
        rooms.get( 23 ).addSide(Direction.EAST, sidesCodex.get( 4 ) );
        rooms.get( 23 ).addSide(Direction.SOUTH, sidesCodex.get( 42 ) );
        rooms.get( 23 ).addSide(Direction.WEST, sidesCodex.get( 36 ) );

        // Fifth Row
        rooms.get( 24 ).addSide(Direction.NORTH, sidesCodex.get( 12 ) );
        rooms.get( 24 ).addSide(Direction.EAST, sidesCodex.get( 15 ) );
        rooms.get( 24 ).addSide(Direction.SOUTH, sidesCodex.get( 9 ) );
        rooms.get( 24 ).addSide(Direction.WEST, sidesCodex.get( 0 ) );

        rooms.get( 25 ).addSide(Direction.NORTH, sidesCodex.get( 0 ) );
        rooms.get( 25 ).addSide(Direction.EAST, sidesCodex.get( 6 ) );
        rooms.get( 25 ).addSide(Direction.SOUTH, sidesCodex.get( 14 ) );
        rooms.get( 25 ).addSide(Direction.WEST, sidesCodex.get( 15 ) );

        rooms.get( 26 ).addSide(Direction.NORTH, sidesCodex.get( 3 ) );
        rooms.get( 26 ).addSide(Direction.EAST, sidesCodex.get( 9 ) );
        rooms.get( 26 ).addSide(Direction.SOUTH, sidesCodex.get( 35 ) );
        rooms.get( 26 ).addSide(Direction.WEST, sidesCodex.get( 20 ) );

        rooms.get( 27 ).addSide(Direction.NORTH, sidesCodex.get( 7 ) );
        rooms.get( 27 ).addSide(Direction.EAST, sidesCodex.get( 4 ) );
        rooms.get( 27 ).addSide(Direction.SOUTH, sidesCodex.get( 18 ) );
        rooms.get( 27 ).addSide(Direction.WEST, sidesCodex.get( 37 ) );

        rooms.get( 28 ).addSide(Direction.NORTH, sidesCodex.get( 35 ) );
        rooms.get( 28 ).addSide(Direction.EAST, sidesCodex.get( 7 ) );
        rooms.get( 28 ).addSide(Direction.SOUTH, sidesCodex.get( 7 ) );
        rooms.get( 28 ).addSide(Direction.WEST, sidesCodex.get( 3 ) );

        rooms.get( 29 ).addSide(Direction.NORTH, sidesCodex.get( 2 ) );
        rooms.get( 29 ).addSide(Direction.EAST, sidesCodex.get( 3 ) );
        rooms.get( 29 ).addSide(Direction.SOUTH, sidesCodex.get( 35 ) );
        rooms.get( 29 ).addSide(Direction.WEST, sidesCodex.get( 7 ) );

        // Sixth Row
        rooms.get( 30 ).addSide(Direction.NORTH, sidesCodex.get( 35 ) );
        rooms.get( 30 ).addSide(Direction.EAST, sidesCodex.get( 38 ) );
        rooms.get( 30 ).addSide(Direction.SOUTH, sidesCodex.get( 3 ) );
        rooms.get( 30 ).addSide(Direction.WEST, sidesCodex.get( 9 ) );

        rooms.get( 31 ).addSide(Direction.NORTH, sidesCodex.get( 18 ) );
        rooms.get( 31 ).addSide(Direction.EAST, sidesCodex.get( 39 ) );
        rooms.get( 31 ).addSide(Direction.SOUTH, sidesCodex.get( 4 ) );
        rooms.get( 31 ).addSide(Direction.WEST, sidesCodex.get( 6 ) );

        rooms.get( 32 ).addSide(Direction.NORTH, sidesCodex.get( 37 ) );
        rooms.get( 32 ).addSide(Direction.EAST, sidesCodex.get( 7 ) );
        rooms.get( 32 ).addSide(Direction.SOUTH, sidesCodex.get( 4 ) );
        rooms.get( 32 ).addSide(Direction.WEST, sidesCodex.get( 40 ) );

        rooms.get( 33 ).addSide(Direction.NORTH, sidesCodex.get( 39 ) );
        rooms.get( 33 ).addSide(Direction.EAST, sidesCodex.get( 3 ) );
        rooms.get( 33 ).addSide(Direction.SOUTH, sidesCodex.get( 0 ) );
        rooms.get( 33 ).addSide(Direction.WEST, sidesCodex.get( 41 ) );

        rooms.get( 34 ).addSide(Direction.NORTH, sidesCodex.get( 42 ) );
        rooms.get( 34 ).addSide(Direction.EAST, sidesCodex.get( 6 ) );
        rooms.get( 34 ).addSide(Direction.SOUTH, sidesCodex.get( 3 ) );
        rooms.get( 34 ).addSide(Direction.WEST, sidesCodex.get( 0 ) );

        rooms.get( 35 ).addSide(Direction.NORTH, sidesCodex.get( 15 ) );
        rooms.get( 35 ).addSide(Direction.EAST, sidesCodex.get( 9 ) );
        rooms.get( 35 ).addSide(Direction.SOUTH, sidesCodex.get( 0 ) );
        rooms.get( 35 ).addSide(Direction.WEST, sidesCodex.get( 18 ) );
    }

    // Places items into specific rooms using the ItemCodex registry
    static private void addItems( List<Room> rooms ) {
        rooms.get( 15 ).addItem( itemCodex.get( "Potion" ) );
        rooms.get( 15 ).addItem( itemCodex.get( "Old sword" ) );
        rooms.get( 15 ).addItem( itemCodex.get( "Leather Armor") );

        rooms.get( 16 ).addItem( itemCodex.get( "Hi-potion" ) );
        rooms.get( 16 ).addItem( itemCodex.get( "Sword" ) );
        rooms.get( 16 ).addItem( itemCodex.get( "Iron Armor") );
    }
}