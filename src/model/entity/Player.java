// Copyright (c) 2026 Bastian Rentzsch

package model.entity;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;

import model.dungeon.Direction;
import model.dungeon.Dungeon;
import model.dungeon.Exit;
import model.dungeon.Room;
import model.items.Inventory;
import model.items.Item;
import model.items.equipments.Accessory;
import model.items.equipments.Armor;
import model.items.equipments.EquipmentSlot;
import model.items.equipments.Equippable;
import model.items.equipments.Headwear;
import model.items.equipments.Weapon;

/**
 * Represents the player character in the game.
 * <p>
 * The player can move through a dungeon, manage an inventory, equip items,
 * and engage in combat with enemies. Movement is based on a grid-based
 * dungeon system with directional exits.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class Player extends Entity implements Serializable {

	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The player's x-coordinate in the dungeon grid.
	 */
	private int x;

	/**
	 * The player's y-coordinate in the dungeon grid.
	 */
	private int y;

	/**
	 * The gender of the player.
	 */
	private String gender;

	/**
	 * The room the player is currently located in.
	 */
	private Room currentRoom;

	/**
	 * The dungeon instance the player is exploring.
	 */
	private Dungeon dungeon;

	/**
	 * The direction the player is currently facing.
	 */
	private Direction facing;

	/**
	 * The player's inventory containing collected items.
	 */
	private final Inventory inventory = new Inventory();

	/**
	 * The currently equipped items mapped by their equipment slot.
	 */
	private final Map<EquipmentSlot, Item> equipment = new EnumMap<>(EquipmentSlot.class);

	/**
	 * The direction of the last movement performed by the player.
	 * Used for mechanics such as fleeing.
	 */
	private Direction lastDirMoved;

    /**
     * Creates a new player in the given dungeon at the specified position.
     *
     * @param name the player's name
     * @param health the initial and maximum health
     * @param x the starting x-coordinate in the dungeon
     * @param y the starting y-coordinate in the dungeon
     * @param gender the gender of the player
     * @param dungeon the dungeon the player is placed in
     */
	public Player(String name, int health, int x, int y, String gender, Dungeon dungeon) {
		super(name, health);
		this.x = x;
		this.y = y;
		this.gender = gender;
		this.currentRoom = dungeon.rooms[x][y];
		this.dungeon = dungeon;
		this.facing = Direction.NORTH;
	}

	/**
     * Returns the player's x-coordinate in the dungeon.
     *
     * @return the x position
     */
	public int getX() {
		return this.x;
	}

	/**
     * Returns the player's y-coordinate in the dungeon.
     *
     * @return the y position
     */
	public int getY() {
		return this.y;
	}

	/**
     * Returns the player's gender.
     *
     * @return the gender string
     */
	public String getGender() {
		return this.gender;
	}

	/**
     * Returns the dungeon the player is currently in.
     *
     * @return the dungeon instance
     */
	public Dungeon getDungeon() {
		return this.dungeon;
	}

	/**
     * Returns the direction the player is currently facing.
     *
     * @return the facing direction
     */
	public Direction getFacing() {
		return this.facing;
	}
	
	/**
     * Returns the opposite direction of the last movement direction.
     * Useful for fleeing mechanics.
     *
     * @return the opposite direction of the last move
     */
	public Direction getDirFlee() {
		return this.lastDirMoved.opposite();
	}

	/**
     * Calculates the player's total attack damage based on equipped weapons.
     *
     * @return the total damage value
     */
	public int getDamage() {
		int damage = 0;

		Weapon weapon = (Weapon) this.equipment.get(EquipmentSlot.WEAPON);

		if (weapon != null) {
			damage += weapon.getDamage();
		}

		return damage;
	}

	/**
     * Calculates the player's total defense based on equipped armor pieces.
     *
     * @return the total defense value
     */
	public int getDefense() {
		int defense = 0;

		Headwear headwear = (Headwear) this.equipment.get(EquipmentSlot.HEADWEAR);
		Armor armor = (Armor) this.equipment.get(EquipmentSlot.ARMOR);
		Accessory accessory = (Accessory) this.equipment.get(EquipmentSlot.ACCESSORY);

		if (headwear != null) {
			defense += headwear.getDefense();
		}

		if (armor != null) {
			defense += armor.getDefense();
		}

		if (accessory != null) {
			defense += accessory.getDefense();
		}

		return defense;
	}

	/**
     * Returns the currently equipped item in the specified slot.
     *
     * @param equipmentSlot the slot to check
     * @return the equipped item, or {@code null} if empty
     */
	public Item getEquipment(EquipmentSlot equipmentSlot) {
		return this.equipment.get(equipmentSlot);
	}

	/**
     * Returns the player's inventory.
     *
     * @return the inventory instance
     */
	public Inventory getInventory() {
	    return this.inventory;
	}

	/**
     * Updates the player's coordinates by the given delta values.
     *
     * @param x change in x direction
     * @param y change in y direction
     */
	public void setCordinates(int x, int y) {
		this.x += x;
		this.y += y;
	}

	/**
     * Equips an item if it is equippable.
     * <p>
     * If an item is already equipped in the same slot, it is moved back to the inventory.
     * </p>
     *
     * @param item the item to equip
     */
	public void equip(Item item) {
    		if (!(item instanceof Equippable equippable )) {
            return;
        }

		EquipmentSlot slot = equippable.getSlot();

        Item previous = this.equipment.put(slot, item);

        if (previous != null) {
            this.inventory.addItem(previous, 1);
        }

        this.inventory.removeItem(item, 1);
    }

	/**
     * Unequips an item from the specified slot and returns it to the inventory.
     *
     * @param slot the equipment slot to clear
     */
	public void unequip(EquipmentSlot slot) {
		 Item previous = this.equipment.put(slot, null);

	     this.inventory.addItem(previous, 1);
	}

	/**
     * Moves the player in the specified direction if possible.
     * <p>
     * Movement is only performed if an exit exists in that direction.
     * The player's position and current room are updated accordingly.
     * </p>
     *
     * @param direction the direction to move in
     */
	public void move(Direction direction) {
		Exit exit = this.currentRoom.getExit(direction);

		if (exit == null) {
			return;
		}

		this.lastDirMoved = direction;
		
		this.currentRoom = exit.target();

		switch (direction) {
			case Direction.NORTH -> {
				this.setCordinates((-1), 0);
			}
			case Direction.SOUTH -> {
				this.setCordinates(1, 0);
			}
			case Direction.EAST -> {
				this.setCordinates(0, 1);
			}
			case Direction.WEST -> {
				this.setCordinates(0, (-1));
			}
		}
	}

	/**
     * Rotates the player 90 degrees to the left.
     */
	public void turnLeft() {
		switch (this.facing) {
			case NORTH -> this.facing = Direction.WEST;
            case WEST -> this.facing = Direction.SOUTH;
            case SOUTH -> this.facing = Direction.EAST;
            case EAST -> this.facing = Direction.NORTH;
        }
    }

	/**
     * Rotates the player 90 degrees to the right.
     */
    public void turnRight() {
        switch (this.facing) {
            case NORTH -> this.facing = Direction.EAST;
            case EAST -> this.facing = Direction.SOUTH;
            case SOUTH -> this.facing = Direction.WEST;
            case WEST -> this.facing = Direction.NORTH;
        }
    }

    /**
     * Attacks the specified enemy, calculating damage based on weapon and enemy defense.
     *
     * @param enemy the enemy to attack
     */
    public void attack(Enemy enemy) {
        int damage = 0;
        int enemyDef = enemy.getDefense();
        int playerDam = this.getDamage();

        if (enemyDef == 0) {
            damage += playerDam;
        }
        else if (enemyDef < playerDam) {
           damage += playerDam - enemyDef;
        }
        else {
            damage += 0;
        }

        enemy.takeDamage(damage);
    }
}
