// Copyright (c) 2026 Bastian Rentzsch

package model.dungeon;

/**
 * Represents the four cardinal directions used for movement and navigation
 * within the dungeon.
 *
 * @author Bastian Rentzsch
 * @since 2026
 */
public enum Direction {

	/** Represents the north direction. */
	NORTH,
	
	/** Represents the east direction. */
	EAST,
	
	/** Represents the south direction. */
	SOUTH,
	
	/** Represents the west direction. */
	WEST;

	/**
     * Returns the opposite of this direction.
     *
     * @return the opposite {@code Direction}
     */
	public Direction opposite() {
		return switch ( this ) {
			case NORTH -> SOUTH;
			case SOUTH -> NORTH;
			case EAST  -> WEST;
			case WEST  -> EAST;
		};
	}
}