// Copyright (c) 2026 Bastian Rentzsch

package model.dungeon;

import java.io.Serializable;

/**
 * Represents an exit connecting a room to another room in a specific direction.
 * <p>
 * Each exit stores the direction in which it can be used and the target room
 * that it leads to.
 * </p>
 *
 * @param direction the direction of the exit
 * @param target the room reached through this exit
 *
 * @author Bastian Rentzsch
 * @since 2026
 */
public record Exit(Direction direction, Room target) implements Serializable {

	/**
     * Serial version UID for serialization.
     */
	private static final long serialVersionUID = 1L;

}
