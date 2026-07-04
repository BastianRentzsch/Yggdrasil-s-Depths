// Copyright (c) 2026 Bastian Rentzsch

package model;

import java.util.Map;

import model.dungeon.Dungeon;
import model.entity.Enemy;
import model.entity.Player;
import model.items.Item;

/**
 * Represents the complete game state.
 * <p>
 * This class aggregates all core game components including the dungeon,
 * player, items, and enemies. It serves as a central data container for
 * saving and loading the full game state.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class Game {
	
	/**
     * The dungeon instance used in the game.
     */
    public Dungeon dungeon;

    /**
     * The player character.
     */
    public Player player;

    /**
     * A map of all available items in the game, indexed by name.
     */
    public Map<String, Item> items;

    /**
     * Array of all enemy templates available in the game.
     */
    public Enemy[] enemies;

    /**
     * Creates a new game instance with the given components.
     *
     * @param dungeon the dungeon structure
     * @param player the player character
     * @param items the item database
     * @param enemies the enemy templates
     */
	public Game(Dungeon dungeon, Player player, Map<String, Item> items, Enemy[] enemies) {
        this.dungeon = dungeon;
        this.player = player;
        this.items = items;
        this.enemies = enemies;
    }
}
