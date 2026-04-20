package dungeon;

import itemSystem.Consumable;
import itemSystem.Weapon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dungeon {
    public static List<Room> createDungeon() {
        Room room1 = new Room();
        Room room2 = new Room();
        Room room3 = new Room();
        Room room4 = new Room();
        Room room5 = new Room();
        Room room6 = new Room();

        Room room7 = new Room();
        Room room8 = new Room();
        Room room9 = new Room();
        Room room10 = new Room();
        Room room11 = new Room();
        Room room12 = new Room();

        Room room13 = new Room();
        Room room14 = new Room();
        Room room15 = new Room();
        Room room16 = new Room();
        Room room17 = new Room();
        Room room18 = new Room();

        Room room19 = new Room();
        Room room20 = new Room();
        Room room21 = new Room();
        Room room22 = new Room();
        Room room23 = new Room();
        Room room24 = new Room();

        Room room25 = new Room();
        Room room26 = new Room();
        Room room27 = new Room();
        Room room28 = new Room();
        Room room29 = new Room();
        Room room30 = new Room();

        Room room31 = new Room();
        Room room32 = new Room();
        Room room33 = new Room();
        Room room34 = new Room();
        Room room35 = new Room();
        Room room36 = new Room();

        // First Row
        room1.addExit( new Exit( Direction.EAST, room2 ) );
        room1.addExit( new Exit( Direction.SOUTH, room7 ) );

        room2.addExit( new Exit( Direction.EAST, room3 ) );
        room2.addExit( new Exit( Direction.SOUTH, room8 ) );
        room2.addExit( new Exit( Direction.WEST, room1 ) );

        room3.addExit( new Exit( Direction.EAST, room4 ) );
        room3.addExit( new Exit( Direction.WEST, room2 ) );

        room4.addExit( new Exit( Direction.WEST, room3 ) );

        room5.addExit( new Exit( Direction.EAST, room6 ) );

        room6.addExit( new Exit( Direction.SOUTH, room12 ) );
        room6.addExit( new Exit( Direction.WEST, room5 ) );

        // Second Row
        room7.addExit( new Exit( Direction.NORTH, room1 ) );
        room7.addExit( new Exit( Direction.SOUTH, room13 ) );

        room8.addExit( new Exit( Direction.NORTH, room2 ) );

        room9.addExit( new Exit( Direction.EAST, room10 ) );
        room9.addExit( new Exit( Direction.SOUTH, room15 ) );

        room10.addExit( new Exit( Direction.EAST, room11 ) );
        room10.addExit( new Exit( Direction.SOUTH, room16 ) );
        room10.addExit( new Exit( Direction.WEST, room9 ) );

        room11.addExit( new Exit( Direction.EAST, room12 ) );
        room11.addExit( new Exit( Direction.WEST, room10 ) );

        room12.addExit( new Exit( Direction.NORTH, room6 ) );
        room12.addExit( new Exit( Direction.SOUTH, room18 ) );
        room12.addExit( new Exit( Direction.WEST, room11 ) );

        // Third Row
        room13.addExit( new Exit( Direction.NORTH, room7 ) );
        room13.addExit( new Exit( Direction.SOUTH, room19 ) );

        room14.addExit( new Exit( Direction.EAST, room15 ) );
        room14.addExit( new Exit( Direction.SOUTH, room20 ) );

        room15.addExit( new Exit( Direction.NORTH, room9 ) );
        room15.addExit( new Exit( Direction.WEST, room14 ) );

        room16.addExit( new Exit( Direction.NORTH, room10 ) );
        room16.addExit( new Exit( Direction.EAST, room17 ) );

        room17.addExit( new Exit( Direction.SOUTH, room23 ) );
        room17.addExit( new Exit( Direction.WEST, room16 ) );

        room18.addExit( new Exit( Direction.NORTH, room12 ) );
        room18.addExit( new Exit( Direction.SOUTH, room24 ) );

        // Fourth Row
        room19.addExit( new Exit( Direction.NORTH, room13 ) );
        room19.addExit( new Exit( Direction.EAST, room20 ) );
        room19.addExit( new Exit( Direction.SOUTH, room25 ) );

        room20.addExit( new Exit( Direction.NORTH, room14 ) );
        room20.addExit( new Exit( Direction.EAST, room21 ) );
        room20.addExit( new Exit( Direction.WEST, room19 ) );

        room21.addExit( new Exit( Direction.EAST, room22 ) );
        room21.addExit( new Exit( Direction.WEST, room20 ) );

        room22.addExit( new Exit( Direction.SOUTH, room28 ) );
        room22.addExit( new Exit( Direction.WEST, room21 ) );

        room23.addExit( new Exit( Direction.NORTH, room17 ) );

        room24.addExit( new Exit( Direction.NORTH, room18 ) );
        room24.addExit( new Exit( Direction.SOUTH, room30 ) );

        // Fifth Row
        room25.addExit( new Exit( Direction.NORTH, room19 ) );

        room26.addExit( new Exit( Direction.EAST, room27 ) );
        room26.addExit( new Exit( Direction.SOUTH, room32 ) );

        room27.addExit( new Exit( Direction.WEST, room26 ) );

        room28.addExit( new Exit( Direction.NORTH, room22 ) );
        room28.addExit( new Exit( Direction.SOUTH, room34 ) );

        room29.addExit( new Exit( Direction.EAST, room30 ) );
        room29.addExit( new Exit( Direction.SOUTH, room35 ) );

        room30.addExit( new Exit( Direction.NORTH, room24 ) );
        room30.addExit( new Exit( Direction.WEST, room29 ) );

        // Sixth Row
        room31.addExit( new Exit( Direction.EAST, room32 ) );

        room32.addExit( new Exit( Direction.NORTH, room26 ) );
        room32.addExit( new Exit( Direction.EAST, room33 ) );
        room32.addExit( new Exit( Direction.WEST, room31 ) );

        room33.addExit( new Exit( Direction.EAST, room34 ) );
        room33.addExit( new Exit( Direction.WEST, room32 ) );

        room34.addExit( new Exit( Direction.NORTH, room28 ) );
        room34.addExit( new Exit( Direction.WEST, room33 ) );

        room35.addExit( new Exit( Direction.NORTH, room29 ) );
        room35.addExit( new Exit( Direction.EAST, room36 ) );

        room36.addExit( new Exit( Direction.WEST, room35 ) );

        // Items
        room16.addItem( new Weapon( "Old sword", "old rusted iron sword", 2 ) );
        room16.addItem( new Weapon( "Sword", "Plain iron sword", 3 ) );
        room16.addItem( new Consumable( "hi-potion", "Heals 20 HP", 20 ) );
        room17.addItem( new Consumable( "Potion", "Heals 10 HP", 10 ) );

        List<Room> rooms = new ArrayList<>( Arrays.asList(
                room1,  room2,  room3,  room4,  room5,  room6,
                room7,  room8,  room9,  room10, room11, room12,
                room13, room14, room15, room16, room17, room18,
                room19, room20, room21, room22, room23, room24,
                room25, room26, room27, room28, room29, room30,
                room31, room32, room33, room34, room35, room36
        ) );

        return rooms;
    }
}
