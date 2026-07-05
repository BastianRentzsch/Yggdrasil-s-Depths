// Copyright (c) 2026 Bastian Rentzsch

package controller;

import model.Game;
import model.dungeon.Direction;
import model.entity.Player;
import model.items.Item;
import model.items.Itemtype;
import model.items.equipments.EquipmentSlot;

import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Utility controller class that provides static methods for creating and
 * managing {@link Player} objects.
 * <p>
 * This class acts as an interface between the application logic and the
 * {@code Player} model. It contains methods for player creation, movement,
 * equipment management, inventory access, and retrieval of player attributes.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class PlayerController {
	
	/**
     * Prevents instantiation of this utility class.
     */
	private PlayerController() {
	}
	
	/**
     * Creates a new player with default attributes and starter items.
     * <p>
     * The player is initialized with predefined health, damage, level,
     * gender, and the current game's dungeon. Additionally, several test
     * items are added to the player's inventory.
     * </p>
     *
     * @param name   the player's name
     * @param gender the player's gender used for sprite selection
     * @param game   the current game instance
     * @return the newly created {@link Player}
     */
	public static Player createNewPlayer(String name, String gender, Game game) {
		Player player = new Player(name, 200, 7, 1, gender, game.dungeon);
		
		// TODO: TEST OBJECTS
		{
		player.getInventory().addItem(game.items.get("Old Sword"), 1);
		player.getInventory().addItem(game.items.get("Sword"), 1);
		player.getInventory().addItem(game.items.get("Divine Sword"), 1);
		player.getInventory().addItem(game.items.get("Leather Helmet"), 1);
		player.getInventory().addItem(game.items.get("Iron Helmet"), 1);
		player.getInventory().addItem(game.items.get("Leather Armor"), 1);
		player.getInventory().addItem(game.items.get("Iron Armor"), 1);
		player.getInventory().addItem(game.items.get("Gold Medal"), 1);
		player.getInventory().addItem(game.items.get("Iron Ring"), 1);
		player.getInventory().addItem(game.items.get("Lesser Health Potion"), 20);
		player.getInventory().addItem(game.items.get("Health Potion"), 20);
		}
		
		return player;
	}
	
	/**
     * Returns the player's name.
     *
     * @param player the player
     * @return the player's name
     */
	public static String getName(Player player) {
		return player.getName();
	}

	/**
     * Returns the player's maximum health.
     *
     * @param player the player
     * @return the maximum health value
     */
	public static int getMaxHealth(Player player) {
		return player.getMaxHealth();
	}

	/**
     * Returns the player's current health.
     *
     * @param player the player
     * @return the current health
     */
	public static int getHealth(Player player) {
		return player.getHealth();
	}
	
	/**
     * Returns the player's current damage value.
     *
     * @param player the player
     * @return the damage value
     */
	public static int getDamage(Player player) {
		return player.getDamage();
	}

	/**
     * Returns the player's current defense value.
     *
     * @param player the player
     * @return the defense value
     */
	public static int getDefense(Player player) {
		return player.getDefense();
	}
	
	/**
     * Returns the file path of the player's sprite image.
     *
     * @param player the player
     * @return the relative image path
     */
	public static String getImagepath(Player player) {
		return "./res/images/sprites/player/"+ player.getGender() + ".png";
	}

	/**
     * Returns all consumable items currently stored in the player's inventory.
     *
     * @param player the player
     * @return a list of consumable items
     */
	public static List<Item> getConsumables(Player player) {
		return player.getInventory().getEquipments(Itemtype.CONSUMABLES);
	}
	
	/**
     * Returns the player's current x-coordinate.
     *
     * @param player the player
     * @return the x-coordinate
     */
	public static int getX(Player player) {
		return player.getX();
	}
	
	/**
     * Returns the player's current y-coordinate.
     *
     * @param player the player
     * @return the y-coordinate
     */
	public static int getY(Player player) {
		return player.getY();
	}
	
	/**
     * Returns the direction the player is currently facing.
     *
     * @param player the player
     * @return the facing direction
     */
	public static Direction getFacing(Player player) {
		return player.getFacing();
	}
	
	/**
     * Returns the item equipped in the specified equipment slot.
     *
     * @param player the player
     * @param slot   the equipment slot
     * @return the equipped item, or {@code null} if the slot is empty
     */
	public static Item getEquipment(Player player, EquipmentSlot slot) {
		return player.getEquipment(slot);
	}
	
	/**
     * Returns all equipment items of the specified item type that are
     * available in the player's inventory.
     *
     * @param player the player
     * @param type   the requested item type
     * @return a list of matching equipment items
     */
	public static List<Item> getEquipments(Player player, Itemtype type) {
		return player.getInventory().getEquipments(type);
	}
	
	/**
     * Returns all inventory entries including their quantities.
     *
     * @param player the player
     * @return a set containing all inventory entries
     */
	public static Set<Entry<Item, Integer>> getInventory(Player player) {
		return player.getInventory().getItems().entrySet();
	}
	
	/**
     * Returns the inventory entry for the specified item name.
     *
     * @param player   the player
     * @param selected the name of the selected item
     * @return the matching inventory entry, or {@code null} if no item exists
     */
	public static Entry<Item, Integer> getItemFromInventory(Player player, String selected) {
		return player.getInventory().getItem(selected);
	}
	
	/**
     * Equips the specified item.
     *
     * @param player the player
     * @param item   the item to equip
     */
	public static void equip(Player player, Item item) {
		player.equip(item);
	}
	
	/**
     * Removes the currently equipped item from the specified equipment slot.
     *
     * @param player the player
     * @param slot   the equipment slot to clear
     */
	public static void unequip(Player player, EquipmentSlot slot) {
		player.unequip(slot);
	}
	
	/**
     * Moves the player one tile in the specified direction.
     *
     * @param player the player
     * @param dir    the movement direction
     */
	public static void move(Player player, Direction dir) {
		player.move(dir);
	}
	
	/**
     * Rotates the player 90 degrees to the left.
     *
     * @param player the player
     */
	public static void turnLeft(Player player) {
		player.turnLeft();
	}
	
	/**
     * Rotates the player 90 degrees to the right.
     *
     * @param player the player
     */
	public static void turnRight(Player player) {
		player.turnRight();
	}
}
