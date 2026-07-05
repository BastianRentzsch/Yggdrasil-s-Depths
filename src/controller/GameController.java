// Copyright (c) 2026 Bastian Rentzsch

package controller;

import java.io.IOException;
import java.util.Map;

import model.Game;
import model.dungeon.Dungeon;
import model.entity.Enemy;
import model.entity.Player;
import model.items.Item;

/**
 * Utility controller class responsible for creating, loading, and managing
 * {@link Game} instances.
 * <p>
 * This class provides static methods for initializing new games, loading
 * saved games, and accessing the main game components such as the player,
 * dungeon, and enemies.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class GameController {
	
	/**
     * Prevents instantiation of this utility class.
     */
	private GameController() {
	}
	
	/**
     * Creates and initializes a new {@link Game}.
     * <p>
     * This method creates a new {@link Dungeon}, loads all available
     * {@link Item} objects and {@link Enemy} definitions, and returns a new
     * game instance without an assigned player.
     *
     * @return a newly initialized {@link Game}
     * @throws ClassNotFoundException if a required class cannot be found while loading game data
     * @throws IOException if an I/O error occurs while loading game resources
     */
	public static Game createNewGame() throws ClassNotFoundException, IOException {
		Dungeon dungeon = new Dungeon();

		Map<String, Item> items = ItemsLoader.load();
		Enemy[]  enemies = EnemiesLoader.load();

		return new Game(dungeon, null, items, enemies);
	}
	
	/**
     * Loads a previously saved {@link Game}.
     * <p>
     * This method restores the saved {@link Player}, reloads all available
     * {@link Item} objects and {@link Enemy} definitions, retrieves the
     * player's associated {@link Dungeon}, and returns a fully initialized
     * game instance.
     *
     * @return the loaded {@link Game}
     * @throws ClassNotFoundException if a required class cannot be found while loading game data
     * @throws IOException if an I/O error occurs while loading the save file or game resources
     */
	public static Game loadGame() throws ClassNotFoundException, IOException {		
		Player player = SavedataHandler.load();
		Map<String, Item> items = ItemsLoader.load();
		Enemy[] enemies = EnemiesLoader.load();
		
		Dungeon dungeon = player.getDungeon();
		
		return new Game(dungeon, player, items, enemies);
	}

	/**
     * Returns all enemies available in the game.
     *
     * @param game the game instance
     * @return an array containing all enemies
     */
	public static Enemy[] getEnemies(Game game) {
		return game.enemies;
	}
	
	/**
     * Returns the current player.
     *
     * @param game the game instance
     * @return the player
     */
	public static Player getPlayer(Game game) {
		return game.player;
	}
	
	/**
     * Returns the game's dungeon.
     *
     * @param game the game instance
     * @return the dungeon
     */
	public static Dungeon getDungeon(Game game) {
		return game.dungeon;
	}
	
	/**
     * Sets the current player for the game.
     *
     * @param game   the game instance
     * @param player the player to assign
     */
	public static void setPlayer(Game game, Player player) {
		game.player = player;
	}
}
