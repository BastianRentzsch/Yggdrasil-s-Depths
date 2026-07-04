// Copyright (c) 2026 Bastian Rentzsch

package model.dungeon;

import java.io.Serializable;

/**
 * Represents a dungeon composed of interconnected rooms arranged in a grid.
 * <p>
 * The dungeon is initialized as a 5x5 grid of {@link Room} objects, where each
 * room is connected to its adjacent neighbors via {@link Exit} objects.
 * Connections are created during construction using predefined links between
 * rooms.
 * </p>
 *
 * @author Bastian Rentzsch
 * @since 2026
 */
public class Dungeon implements Serializable {

	/**
     * Serial version UID for serialization.
     */
	private static final long serialVersionUID = 1L;

	/**
     * Two-dimensional grid of rooms representing the dungeon layout.
     */
	public Room[][] rooms;

	/**
     * Creates a new dungeon and initializes all rooms and their connections.
     * <p>
     * Each room is placed in a 5x5 grid and connected to adjacent rooms using
     * directional exits.
     * </p>
     */
	public Dungeon() {
		//|X  -Y
		this.rooms = new Room[8][6];
		for (int i = 0; i < this.rooms.length; i++) {
			for (int j = 0; j < this.rooms[i].length; j++) {
				this.rooms[i][j] = new Room();
			}
		}

		// row 0
		connect(0, 1, Direction.EAST, 	0, 2);

		connect(0, 2, Direction.EAST, 	0, 3);
		connect(0, 2, Direction.SOUTH, 	1, 2);

		connect(0, 3, Direction.EAST, 	0, 4);

		connect(0, 4, Direction.EAST, 	0, 5);
		connect(0, 4, Direction.SOUTH, 	1, 4);
		
		connect(0, 5, Direction.SOUTH, 	1, 5);

		// row 1
		connect(1, 0, Direction.SOUTH,	2, 0);

		connect(1, 2, Direction.SOUTH, 	2, 2);

		connect(1, 4, Direction.EAST, 	1, 5);
		connect(1, 4, Direction.SOUTH, 	2, 4);

		// row 2
		connect(2, 0, Direction.EAST, 	2, 1);
		
		connect(2, 1, Direction.EAST, 	2, 2);

		connect(2, 4, Direction.SOUTH, 	3, 4);

		// row 3
		connect(3, 4, Direction.SOUTH, 	4, 4);

		// row 4
		connect(4, 1, Direction.EAST, 	4, 2);
		connect(4, 1, Direction.SOUTH, 	5, 1);

		connect(4, 2, Direction.EAST, 	4, 3);

		connect(4, 3, Direction.EAST, 	4, 4);
		
		connect(4, 4, Direction.EAST, 	4, 5);
		
		connect(4, 5, Direction.SOUTH, 	5, 5);
		
		// row 5
		connect(5, 1, Direction.SOUTH, 	6, 1);

		connect(5, 5, Direction.SOUTH, 	6, 5);
		
		// row 6
		connect(6, 1, Direction.SOUTH, 	7, 1);

		connect(6, 3, Direction.SOUTH, 	7, 3);

		connect(6, 5, Direction.SOUTH, 	7, 5);
		
		// row 7
		connect(7, 3, Direction.EAST, 	7, 4);
		
		connect(7, 4, Direction.EAST, 	7, 5);
	}

	/**
     * Connects two rooms bidirectionally using exits.
     *
     * @param x1 row index of the first room
     * @param y1 column index of the first room
     * @param dir direction from the first room to the second room
     * @param x2 row index of the second room
     * @param y2 column index of the second room
     */
	private void connect(int x1, int y1, Direction dir, int x2, int y2) {
		Room a = this.rooms[x1][y1];
		Room b = this.rooms[x2][y2];

		a.addExit(new Exit(dir, b));
		b.addExit(new Exit(dir.opposite(), a));
	}
}
