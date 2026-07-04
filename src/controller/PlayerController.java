// Copyright (c) 2026 Bastian Rentzsch

package controller;

import model.Game;
import model.entity.Player;

/**
 * Utility class responsible for creating and initializing {@link Player} objects.
 * <p>
 * This class cannot be instantiated and only provides static methods.
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
     * Creates a new {@link Player} with the default starting attributes and
     * assigns the player to the dungeon of the specified {@link Game}.
     * <p>
     * The player is initialized with:
     * <ul>
     *   <li>200 maximum health</li>
     *   <li>7 inventory slots (or another default capacity, depending on the {@code Player} implementation)</li>
     *   <li>Level 1</li>
     * </ul>
     * <p>
     * <strong>Note:</strong> The currently included items are intended for
     * testing purposes only and should be removed or replaced in production.
     *
     * @param name the player's name
     * @param gender the player's gender
     * @param game the game instance containing the dungeon and available items
     * @return a newly created and initialized {@link Player}
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
}
