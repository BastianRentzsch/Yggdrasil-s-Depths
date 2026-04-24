// Copyright (c) 2026 Bastian Rentzsch

package entitySystem;

import dungeon.Direction;
import dungeon.Exit;
import dungeon.Room;
import dungeon.Side;
import game.EncounterSystem;
import game.Game;
import itemSystem.*;

import java.util.EnumMap;
import java.util.Map;

// Represents the player character, including movement, inventory, and equipment
public class Player extends Entity {
    private Room currentRoom;
    private final Map<EquipmentSlot, Item> equipment = new EnumMap<>( EquipmentSlot.class );
    private Direction facing = Direction.NORTH;

    public Player( String name, int health, Room startRoom ) {
        super( name, health );
        this.currentRoom = startRoom;
    }

    // Returns a visual health bar representing the player's current health
    public String getHealth() {
        int barLength = 20;
        double healthPercent = ( double ) this.health / this.maxHealth;

        int filled = ( int ) ( barLength * healthPercent );
        int empty = barLength - filled;

        StringBuilder bar = new StringBuilder();

        bar.repeat( "█", Math.max( 0, filled ) );
        bar.repeat( " ", Math.max( 0, empty ) );

        return bar.toString();
    }

    // Calculates and returns the player's attack damage based on equipped weapon
    public int getDamage() {
        int damage;
        if ( getEquipped( EquipmentSlot.WEAPON ) == null ) {
            damage = 0;
        }
        else {
            damage = ( ( Weapon ) getEquipped( EquipmentSlot.WEAPON ) ).getDamage();
        }

        return damage;
    }

    // Calculates and returns the player's defense based on equipped armor
    public int getDefense() {
        int defense;
        if ( getEquipped( EquipmentSlot.ARMOR ) == null ) {
            defense = 0;
        }
        else {
            defense = ( ( Armor ) getEquipped( EquipmentSlot.ARMOR ) ).getDefense();
        }

        return defense;
    }

    // Returns the room the player is currently in
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    // Returns the item equipped in a specific slot
    public Item getEquipped( EquipmentSlot slot ) {
        return equipment.get( slot );
    }

    // Returns the direction the player is currently facing
    public Direction getFacing() {
        return this.facing;
    }

    // Turns the player 90 degrees to the left
    public void turnLeft() {
        switch ( this.facing ) {
            case NORTH -> this.facing = Direction.WEST;
            case WEST -> this.facing = Direction.SOUTH;
            case SOUTH -> this.facing = Direction.EAST;
            case EAST -> this.facing = Direction.NORTH;
        }
    }

    // Turns the player 90 degrees to the right
    public void turnRight() {
        switch ( this.facing ) {
            case NORTH -> this.facing = Direction.EAST;
            case EAST -> this.facing = Direction.SOUTH;
            case SOUTH -> this.facing = Direction.WEST;
            case WEST -> this.facing = Direction.NORTH;
        }
    }

    // Shows what is in front of the player based on the current facing direction
    public void look() {
        Side side = this.currentRoom.getSide( this.facing );

        if (side == null) {
            System.out.println( "You see nothing special." );
            return;
        }

        System.out.println( side.art() );
    }

    // Moves the player in a given direction if an exit exists
    public void move( Direction direction, Game game ) {
        Exit exit = this.currentRoom.getExit( direction );

        if ( exit == null ) {
            System.out.println( "The way is blocked." );
            return;
        }

        this.currentRoom = exit.target();

        System.out.println( "You moved " + direction + "." );

        EncounterSystem.checkForEncounter( game );
    }

    // Adds an item to the player's inventory
    public void pickUp( Item item ) {
        this.inventory.add( item );
        System.out.println( "Picked up: " + item.getName() );
    }

    // Equips an item if it is equippable, swapping with existing equipment if needed
    public void equip( Item item ) {
        if ( !( item instanceof Equippable equippable ) ) {
            System.out.println( "You can't equip that." );
            return;
        }

        EquipmentSlot slot = equippable.getSlot();

        Item previous = this.equipment.put( slot, item );

        if ( previous != null ) {
            System.out.println( "Unequipped " + previous.getName() );
            this.inventory.add( previous );
        }

        this.inventory.remove( item );

        System.out.println( "Equipped " + item.getName() );
    }

    // Drops an item from inventory into the current room
    public void drop( Item item ) {
        this.inventory.remove( item );
        this.currentRoom.addItem( item );
        System.out.println( "Dropped: " + item.getName() );
    }
}