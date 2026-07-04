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
 * Utility class for creating and loading {@link Game} instances.
 * <p>
 * This class provides static methods to initialize a new game or restore
 * a previously saved game. It cannot be instantiated.
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
}
