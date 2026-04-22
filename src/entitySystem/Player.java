package entitySystem;

import dungeon.Direction;
import dungeon.Exit;
import dungeon.Room;
import dungeon.Side;
import itemSystem.*;

import java.util.EnumMap;
import java.util.Map;

public class Player extends Entity {
    private Room currentRoom;
    private final Map<EquipmentSlot, Item> equipment = new EnumMap<>(EquipmentSlot.class);
    private Direction facing = Direction.NORTH;

    public Player( String name, Room startRoom ) {
        super( name );
        this.currentRoom = startRoom;
    }

    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    public Item getEquipped( EquipmentSlot slot ) {
        return equipment.get( slot );
    }

    public Direction getFacing() {
        return facing;
    }

    public void turnLeft() {
        switch ( facing ) {
            case NORTH -> facing = Direction.WEST;
            case WEST -> facing = Direction.SOUTH;
            case SOUTH -> facing = Direction.EAST;
            case EAST -> facing = Direction.NORTH;
        }
    }

    public void turnRight() {
        switch ( facing ) {
            case NORTH -> facing = Direction.EAST;
            case EAST -> facing = Direction.SOUTH;
            case SOUTH -> facing = Direction.WEST;
            case WEST -> facing = Direction.NORTH;
        }
    }

    public void look() {
        Side side = currentRoom.getSide( facing );

        if (side == null) {
            System.out.println( "You see nothing special." );
            return;
        }

        System.out.println( side.getArt() );
    }

    public void move( Direction direction ) {
        Exit exit = currentRoom.getExit( direction );

        if ( exit == null ) {
            System.out.println( "No exit in that direction." );
            return;
        }

        if ( !exit.canPass( this ) ) {
            System.out.println( "The way is blocked." );
            return;
        }

        this.currentRoom = exit.getTarget();

        System.out.println( "You moved " + direction + "." );
    }

    public void pickUp( Item item ) {
        inventory.add( item );
        System.out.println( "Picked up: " + item.getName() );
    }

    public void equip( Item item ) {
        if ( !( item instanceof Equippable equippable ) ) {
            System.out.println( "You can't equip that." );
            return;
        }

        EquipmentSlot slot = equippable.getSlot();

        Item previous = equipment.put( slot, item );

        if ( previous != null ) {
            System.out.println( "Unequipped " + previous.getName() );
            inventory.add( previous );
        }

        inventory.remove( item );

        System.out.println( "Equipped " + item.getName() );
    }
}