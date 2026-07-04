// Copyright (c) 2026 Bastian Rentzsch

package model.dungeon;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;

/**
 * Represents a room in the dungeon.
 * <p>
 * A room can contain multiple exits, each associated with one of the cardinal
 * directions. These exits connect the room to neighboring rooms.
 * </p>
 *
 * @author Bastian Rentzsch
 * @since 2026
 */
public class Room implements Serializable {

	/**
     * Serial version UID for serialization.
     */
	private static final long serialVersionUID = 1L;

	/**
     * The exits leading from this room, mapped by their direction.
     */
    public final Map<Direction, Exit> exits = new EnumMap<>( Direction.class );

    /**
     * Adds an exit to this room.
     *
     * @param exit the exit to add
     */
    public void addExit(Exit exit) {
    		this.exits.put(exit.direction(), exit);
    }

    /**
     * Returns the exit in the specified direction.
     *
     * @param direction the direction of the requested exit
     * @return the corresponding {@link Exit}, or {@code null} if no exit exists
     *         in that direction
     */
    public Exit getExit(Direction direction) {
    		return this.exits.get(direction);
    }

}
