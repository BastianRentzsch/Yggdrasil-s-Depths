// Copyright (c) 2026 Bastian Rentzsch

package controller;

import model.dungeon.Direction;
import model.dungeon.Dungeon;

/**
 * Utility class that provides helper methods for accessing
 * dungeon-related data.
 * <p>
 * This class contains only static methods and cannot be instantiated.
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class DungeonController {

	/**
     * Private constructor to prevent instantiation of this utility class.
     */
	private DungeonController() {
	}
	
	/**
     * Returns the number of rooms along the x-axis of the dungeon.
     *
     * @param dungeon the dungeon whose x-axis length should be retrieved
     * @return the number of rooms on the x-axis
     */
	public static int getXLength(Dungeon dungeon) {
		return dungeon.rooms.length;
	}
	
	/**
     * Returns the number of rooms along the y-axis of the dungeon.
     *
     * @param dungeon the dungeon whose y-axis length should be retrieved
     * @return the number of rooms on the y-axis
     */
	public static int getYLength(Dungeon dungeon) {
		return dungeon.rooms[0].length;
	}
	
	/**
     * Checks whether the room at the specified coordinates contains
     * an exit in the given direction.
     *
     * @param dungeon the dungeon containing the room
     * @param x the x-coordinate of the room
     * @param y the y-coordinate of the room
     * @param dir the direction of the exit to check
     * @return {@code true} if the room contains an exit in the specified
     *         direction; {@code false} otherwise
     */
	public static boolean roomContainsExit(Dungeon dungeon, int x, int y, Direction dir) {
		return dungeon.rooms[x][y].exits.containsKey(dir);
	}
}
