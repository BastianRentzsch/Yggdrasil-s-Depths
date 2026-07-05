// Copyright (c) 2026 Bastian Rentzsch

package view.gamewindow;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import controller.DungeonController;
import model.dungeon.Direction;
import model.dungeon.Dungeon;

/**
 * Utility class responsible for rendering a pseudo-3D first-person view
 * of the dungeon based on the player's position and facing direction.
 * <p>
 * The renderer uses preloaded layered images (ceiling and wall segments)
 * to construct a composite view of the current room and surrounding
 * structures. Visibility is determined by checking dungeon exits in
 * relative positions around the player.
 * </p>
 * <p>
 * Images are loaded once during initialization and cached for reuse.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class DungeonImage {

	/**
     * The dungeon data used for rendering visibility and room structure.
     */
    private final Dungeon dungeon;

    /**
     * Cache of preloaded images mapped by a string key.
     */
    private final Map<String, BufferedImage> images = new HashMap<>();

    /**
     * Creates a new dungeon renderer for the given dungeon.
     *
     * @param dungeon the dungeon to render
     */
	public DungeonImage(Dungeon dungeon) {
		this.dungeon = dungeon;
		this.loadImages();
	}

	/**
     * Loads all required dungeon graphics into memory.
     * <p>
     * This includes empty base textures and layered ceiling/wall assets
     * for multiple rendering layers. If an image cannot be loaded, it
     * is silently skipped.
     * </p>
     *
     * @throws RuntimeException if a critical loading error occurs
     */
	private void loadImages() {
		try {
			this.load("empty", "empty.png");

			for (int layer = 1; layer <= 3; layer++) {
				for (int i = 1; i <= 6; i++) {
					this.load(
						"layer_" + layer + "_ceiling_" + i,
						"layer_" + layer + "/ceiling_" + i + ".png"
					);

					this.load(
						"layer_" + layer + "_wall_" + i,
						"layer_" + layer + "/wall_" + i + ".png"
					);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException("Error loading dungeon graphics", e);
		}
	}

	/**
     * Loads a single image into the cache if it exists on disk.
     *
     * @param key logical identifier used for rendering
     * @param path relative file path within the dungeon image folder
     * @throws IOException if the image cannot be read
     */
	private void load(String key, String path) throws IOException {
		File file = new File("./res/images/dungeon/" + path);
		if (file.exists()) {
			this.images.put(key, ImageIO.read(file));
		}
	}

	/**
     * Retrieves a cached image by key.
     *
     * @param key the image identifier
     * @return the cached image, or {@code null} if not found
     */
	private BufferedImage get(String key) {
		return this.images.get(key);
	}

	/**
     * Draws a cached image onto the given graphics context.
     *
     * @param g the graphics context
     * @param key the image key
     * @param x x-position
     * @param y y-position
     */
	private void draw(Graphics g, String key, int x, int y) {
		BufferedImage img = this.get(key);
		if (img != null) {
			g.drawImage(img, x, y, null);
		}
	}

	/**
     * Checks whether a room exists at the given coordinates.
     *
     * @param x room x-coordinate
     * @param y room y-coordinate
     * @return {@code true} if the room exists, otherwise {@code false}
     */
	private boolean roomExists(int x, int y) {
		return x >= 0 && y >= 0
			&& x < DungeonController.getXLength(this.dungeon)
			&& y < DungeonController.getYLength(this.dungeon);
	}

	/**
     * Checks whether a room at the given position has an exit in the
     * specified direction.
     *
     * @param x room x-coordinate
     * @param y room y-coordinate
     * @param dir direction to check
     * @return {@code true} if an exit exists, otherwise {@code false}
     */
	private boolean hasExit(int x, int y, Direction dir) {
		return this.roomExists(x, y)
				&& DungeonController.roomContainsExit(this.dungeon, x, y, dir);
	}

	/**
     * Determines whether a relative position is blocked (no valid exit).
     *
     * @param x base x-coordinate
     * @param y base y-coordinate
     * @param c relative exit check
     * @return {@code true} if blocked, otherwise {@code false}
     */
	private boolean isBlocked(int x, int y, ExitCheck c) {
		return !this.hasExit(x + c.dx(), y + c.dy(), c.dir());
	}

	/**
     * Checks whether any of the given exit checks are blocked.
     *
     * @param x base x-coordinate
     * @param y base y-coordinate
     * @param checks array of exit checks
     * @return {@code true} if at least one check is blocked
     */
	private boolean anyBlocked(int x, int y, ExitCheck... checks) {
		for (ExitCheck c : checks) {
	        if (this.isBlocked(x, y, c)) {
	            return true;
	        }
		}
		return false;
	}

	/**
     * Renders the current first-person dungeon view as a composed image.
     *
     * @param x player x-coordinate
     * @param y player y-coordinate
     * @param facing direction the player is facing
     * @return rendered view as a {@link BufferedImage}
     */
	public BufferedImage paintCurrentView(int x, int y, Direction facing) {
		BufferedImage canvas = new BufferedImage(
			this.get("empty").getWidth(),
			this.get("empty").getHeight(),
			BufferedImage.TYPE_INT_ARGB
		);

		Graphics g = canvas.getGraphics();

		this.draw(g, "layer_3_ceiling_1", 0, 0);

		if (facing == Direction.NORTH) {
			if (this.anyBlocked(x, y, new ExitCheck(-2, -2, Direction.SOUTH))
					&& this.anyBlocked(x, y, new ExitCheck(-2, -1, Direction.WEST))) {
				this.draw(g, "layer_3_wall_1", 0, 0);
			}
		}
		else if (facing == Direction.SOUTH) {
			if ((this.anyBlocked(x, y, new ExitCheck(2, 2, Direction.WEST))
						&& this.anyBlocked(x, y, new ExitCheck(1, 2, Direction.SOUTH)))
					|| (this.anyBlocked(x, y, new ExitCheck(2, 2, Direction.NORTH))
						&& this.anyBlocked(x, y, new ExitCheck(2, 1, Direction.EAST)))) {
				this.draw(g, "layer_3_wall_1", 0, 0);
			}
		}
		else if (facing == Direction.WEST) {
			if (this.anyBlocked(x, y, new ExitCheck(2, -2, Direction.EAST))
					&& this.anyBlocked(x, y, new ExitCheck(1, -2, Direction.SOUTH))) {
		        this.draw(g, "layer_3_wall_1", 0, 0);
		    }
		}
		else if (facing == Direction.EAST) {
			if (this.anyBlocked(x, y, new ExitCheck(-2, 2, Direction.WEST))
					&& this.anyBlocked(x, y, new ExitCheck(-1, 2, Direction.NORTH))) {
		        this.draw(g, "layer_3_wall_1", 0, 0);
		    }
		}

		this.draw(g, "layer_3_ceiling_2", 0, 0);
		this.draw(g, "layer_3_ceiling_6", 80, 0);

		if (facing == Direction.NORTH) {
			if (this.anyBlocked(x, y, new ExitCheck(-2, 2, Direction.SOUTH))
					&& this.anyBlocked(x, y, new ExitCheck(-2, 1, Direction.EAST))) {
				this.draw(g, "layer_3_wall_4", 80, 0);
			}
		}
		else if (facing == Direction.SOUTH) {
			if (this.anyBlocked(x, y, new ExitCheck(2, -2, Direction.NORTH))
					&& this.anyBlocked(x, y, new ExitCheck(2, -1, Direction.WEST))) {
				this.draw(g, "layer_3_wall_4", 80, 0);
			}
		}
		else if (facing == Direction.WEST) {
			if (this.anyBlocked(x, y, new ExitCheck(-2, -2, Direction.EAST))
					&& this.anyBlocked(x, y, new ExitCheck(-1, -2, Direction.NORTH))) {
				this.draw(g, "layer_3_wall_4", 80, 0);
		    }
		}
		else if (facing == Direction.EAST) {
			if (this.anyBlocked(x, y, new ExitCheck(2, 2, Direction.WEST))
					&& this.anyBlocked(x, y, new ExitCheck(1, 2, Direction.SOUTH))) {
		        this.draw(g, "layer_3_wall_4", 80, 0);
		    }
		}

		this.draw(g, "layer_3_ceiling_5", 80, 0);
		this.draw(g, "layer_2_ceiling_1", 0, 0);

		if (facing == Direction.NORTH) {
			if (this.anyBlocked(x, y, new ExitCheck(-1, -1, Direction.WEST))) {
				this.draw(g, "layer_2_wall_1", 0, 0);
			}
		}
		else if (facing == Direction.SOUTH) {
			if (this.anyBlocked(x, y, new ExitCheck(1, 1, Direction.EAST))) {
				this.draw(g, "layer_2_wall_1", 0, 0);
		    }
		}
		else if (facing == Direction.WEST) {
			if (this.anyBlocked(x, y, new ExitCheck(1, -1, Direction.SOUTH))) {
		        this.draw(g, "layer_2_wall_1", 0, 0);
		    }
		}
		else if (facing == Direction.EAST) {
			if (this.anyBlocked(x, y, new ExitCheck(-1, 1, Direction.NORTH))) {
		        this.draw(g, "layer_2_wall_1", 0, 0);
		    }
		}

		if (facing == Direction.NORTH) {
			if (this.anyBlocked(x, y, new ExitCheck(-2, 0, Direction.WEST))
					&& this.anyBlocked(x, y, new ExitCheck(-1, -1, Direction.NORTH))) {
				this.draw(g, "layer_3_wall_2", 0, 0);
			}
		}
		else if (facing == Direction.SOUTH) {
			if ((this.anyBlocked(x, y, new ExitCheck(2, 1, Direction.WEST))
						&& this.anyBlocked(x, y, new ExitCheck(1, 1, Direction.SOUTH)))
					|| (this.anyBlocked(x, y, new ExitCheck(2, 0, Direction.EAST))
							&& this.anyBlocked(x, y, new ExitCheck(1, 1, Direction.SOUTH)))) {
		        this.draw(g, "layer_3_wall_2", 0, 0);
		    }
		}
		else if (facing == Direction.WEST) {
			if (this.anyBlocked(x, y, new ExitCheck(0, -2, Direction.SOUTH))
					&& this.anyBlocked(x, y, new ExitCheck(1, -1, Direction.WEST))) {
				this.draw(g, "layer_3_wall_2", 0, 0);
		    }
		}
		else if (facing == Direction.EAST) {
			if (this.anyBlocked(x, y, new ExitCheck(0, 2, Direction.NORTH))
					&& this.anyBlocked(x, y, new ExitCheck(-1, 1, Direction.EAST))) {
		        this.draw(g, "layer_3_wall_2", 0, 0);
		    }
		}

		this.draw(g, "layer_3_ceiling_3", 0, 0);
		this.draw(g, "layer_2_ceiling_2", 0, 0);

		if (facing == Direction.NORTH) {
			if (this.anyBlocked(x, y, new ExitCheck(-1, 0, Direction.NORTH))) {
				this.draw(g, "layer_2_wall_3", 0, 0);
			}
		}
		else if (facing == Direction.SOUTH) {
			if (this.anyBlocked(x, y, new ExitCheck(1, 0, Direction.SOUTH))) {
		        this.draw(g, "layer_2_wall_3", 0, 0);
		    }
		}
		else if (facing == Direction.WEST) {
			if (this.anyBlocked(x, y, new ExitCheck(0, -1, Direction.WEST))) {
		        this.draw(g, "layer_2_wall_3", 0, 0);
		    }
		}
		else if (facing == Direction.EAST) {
			if (this.anyBlocked(x, y, new ExitCheck(0, 1, Direction.EAST))) {
		        this.draw(g, "layer_2_wall_3", 0, 0);
		    }
		}

		if (facing == Direction.NORTH) {
			if (this.anyBlocked(x, y, new ExitCheck(-1, 0, Direction.WEST))
					&& this.anyBlocked(x, y, new ExitCheck(0, -1, Direction.NORTH))) {
				this.draw(g, "layer_2_wall_2", 0, 0);
			}
		}
		else if (facing == Direction.SOUTH) {
			if (this.anyBlocked(x, y, new ExitCheck(1, 0, Direction.EAST))
		            && this.anyBlocked(x, y, new ExitCheck(0, 1, Direction.SOUTH))) {
		        this.draw(g, "layer_2_wall_2", 0, 0);
		    }
		}
		else if (facing == Direction.WEST) {
			if (this.anyBlocked(x, y, new ExitCheck(0, -1, Direction.SOUTH))
		            && this.anyBlocked(x, y, new ExitCheck(1, 0, Direction.WEST))) {
		        this.draw(g, "layer_2_wall_2", 0, 0);
		    }
		}
		else if (facing == Direction.EAST) {
			if (this.anyBlocked(x, y, new ExitCheck(0, 1, Direction.NORTH))
		            && this.anyBlocked(x, y, new ExitCheck(-1, 0, Direction.EAST))) {
		        this.draw(g, "layer_2_wall_2", 0, 0);
		    }
		}

		this.draw(g, "layer_2_ceiling_3", 0, 0);
		this.draw(g, "layer_2_ceiling_6", 80, 0);

		if (facing == Direction.NORTH) {
			if (this.anyBlocked(x, y, new ExitCheck(-1, 1, Direction.EAST))) {
				this.draw(g, "layer_2_wall_6", 80, 0);
			}
		}
		else if (facing == Direction.SOUTH) {
			if (this.anyBlocked(x, y, new ExitCheck(1, -1, Direction.WEST))) {
		        this.draw(g, "layer_2_wall_6", 80, 0);
		    }
		}
		else if (facing == Direction.WEST) {
			if (this.anyBlocked(x, y, new ExitCheck(-1, -1, Direction.NORTH))) {
		        this.draw(g, "layer_2_wall_6", 80, 0);
		    }
		}
		else if (facing == Direction.EAST) {
			if (this.anyBlocked(x, y, new ExitCheck(1, 1, Direction.SOUTH))) {
		        this.draw(g, "layer_2_wall_6", 80, 0);
		    }
		}

		if (facing == Direction.NORTH) {
			if (this.anyBlocked(x, y, new ExitCheck(-2, 0, Direction.EAST))
					&& this.anyBlocked(x, y, new ExitCheck(-1, 1, Direction.NORTH))) {
				this.draw(g, "layer_3_wall_3", 80, 0);
			}
		}
		else if (facing == Direction.SOUTH) {
			if (this.anyBlocked(x, y, new ExitCheck(2, 0, Direction.WEST))
					&& this.anyBlocked(x, y, new ExitCheck(1, -1, Direction.SOUTH))) {
		        this.draw(g, "layer_3_wall_3", 80, 0);
		    }
		}
		else if (facing == Direction.WEST) {
			if (this.anyBlocked(x, y, new ExitCheck(0, -2, Direction.NORTH))
					&& this.anyBlocked(x, y, new ExitCheck(-1, -1, Direction.WEST))) {
		        this.draw(g, "layer_3_wall_3", 80, 0);
		    }
		}
		else if (facing == Direction.EAST) {
			if (this.anyBlocked(x, y, new ExitCheck(0, 2, Direction.SOUTH))
		            && this.anyBlocked(x, y, new ExitCheck(1, 1, Direction.EAST))) {
		        this.draw(g, "layer_3_wall_3", 80, 0);
		    }
		}

		this.draw(g, "layer_3_ceiling_4", 80, 0);
		this.draw(g, "layer_2_ceiling_5", 80, 0);

		if (facing == Direction.NORTH) {
			if (this.anyBlocked(x, y, new ExitCheck(-1, 0, Direction.NORTH))) {
				this.draw(g, "layer_2_wall_4", 80, 0);
			}
		}
		else if (facing == Direction.SOUTH) {
			if (this.anyBlocked(x, y, new ExitCheck(1, 0, Direction.SOUTH))) {
		        this.draw(g, "layer_2_wall_4", 80, 0);
		    }
		}
		else if (facing == Direction.WEST) {
			if (this.anyBlocked(x, y, new ExitCheck(0, -1, Direction.WEST))) {
		        this.draw(g, "layer_2_wall_4", 80, 0);
		    }
		}
		else if (facing == Direction.EAST) {
			if (this.anyBlocked(x, y, new ExitCheck(0, 1, Direction.EAST))) {
		        this.draw(g, "layer_2_wall_4", 80, 0);
		    }
		}

		if (facing == Direction.NORTH) {
			if (this.anyBlocked(x, y, new ExitCheck(-1, 0, Direction.EAST))
					&& this.anyBlocked(x, y, new ExitCheck(0, 1, Direction.NORTH))) {
				this.draw(g, "layer_2_wall_5", 80, 0);
			}
		}
		else if (facing == Direction.SOUTH) {
			if (this.anyBlocked(x, y, new ExitCheck(1, 0, Direction.WEST))
		            && this.anyBlocked(x, y, new ExitCheck(0, -1, Direction.SOUTH))) {
		        this.draw(g, "layer_2_wall_5", 80, 0);
		    }
		}
		else if (facing == Direction.WEST) {
			if (this.anyBlocked(x, y, new ExitCheck(0, -1, Direction.NORTH))
		            && this.anyBlocked(x, y, new ExitCheck(-1, 0, Direction.WEST))) {
		        this.draw(g, "layer_2_wall_5", 80, 0);
		    }
		}
		else if (facing == Direction.EAST) {
			if (this.anyBlocked(x, y, new ExitCheck(0, 1, Direction.SOUTH))
		            && this.anyBlocked(x, y, new ExitCheck(1, 0, Direction.EAST))) {
		        this.draw(g, "layer_2_wall_5", 80, 0);
		    }
		}

		this.draw(g, "layer_2_ceiling_4", 80, 0);
		this.draw(g, "layer_1_ceiling_1", 0, 0);

		if (facing == Direction.NORTH) {
			if (this.anyBlocked(x, y, new ExitCheck(0, 0, Direction.WEST))) {
				this.draw(g, "layer_1_wall_1", 0, 0);
			}
		}
		else if (facing == Direction.SOUTH) {
			if (this.anyBlocked(x, y, new ExitCheck(0, 0, Direction.EAST))) {
		        this.draw(g, "layer_1_wall_1", 0, 0);
		    }
		}
		else if (facing == Direction.WEST) {
			if (this.anyBlocked(x, y, new ExitCheck(0, 0, Direction.SOUTH))) {
		        this.draw(g, "layer_1_wall_1", 0, 0);
		    }
		}
		else if (facing == Direction.EAST) {
			if (this.anyBlocked(x, y, new ExitCheck(0, 0, Direction.NORTH))) {
		        this.draw(g, "layer_1_wall_1", 0, 0);
		    }
		}

		if (facing == Direction.NORTH) {
			if (this.anyBlocked(x, y, new ExitCheck(0, 0, Direction.NORTH))) {
				this.draw(g, "layer_1_wall_2", 0, 0);
			}
		}
		else if (facing == Direction.SOUTH) {
			if (this.anyBlocked(x, y, new ExitCheck(0, 0, Direction.SOUTH))) {
		        this.draw(g, "layer_1_wall_2", 0, 0);
		    }
		}
		else if (facing == Direction.WEST) {
			if (this.anyBlocked(x, y, new ExitCheck(0, 0, Direction.WEST))) {
		        this.draw(g, "layer_1_wall_2", 0, 0);
		    }
		}
		else if (facing == Direction.EAST) {
			if (this.anyBlocked(x, y, new ExitCheck(0, 0, Direction.EAST))) {
		        this.draw(g, "layer_1_wall_2", 0, 0);
		    }
		}

		this.draw(g, "layer_1_ceiling_2", 0, 0);
		this.draw(g, "layer_1_ceiling_4", 80, 0);

		if (facing == Direction.NORTH) {
			if (this.anyBlocked(x, y, new ExitCheck(0, 0, Direction.EAST))) {
				this.draw(g, "layer_1_wall_4", 80, 0);
			}
		}
		else if (facing == Direction.SOUTH) {
			if (this.anyBlocked(x, y, new ExitCheck(0, 0, Direction.WEST))) {
		        this.draw(g, "layer_1_wall_4", 80, 0);
		    }
		}
		else if (facing == Direction.WEST) {
			if (this.anyBlocked(x, y, new ExitCheck(0, 0, Direction.NORTH))) {
		        this.draw(g, "layer_1_wall_4", 80, 0);
		    }
		}
		else if (facing == Direction.EAST) {
			if (this.anyBlocked(x, y, new ExitCheck(0, 0, Direction.SOUTH))) {
		        this.draw(g, "layer_1_wall_4", 80, 0);
		    }
		}

		if (facing == Direction.NORTH) {
			if (this.anyBlocked(x, y, new ExitCheck(0, 0, Direction.NORTH))) {
				this.draw(g, "layer_1_wall_3", 80, 0);
			}
		}
		else if (facing == Direction.SOUTH) {
			if (this.anyBlocked(x, y, new ExitCheck(0, 0, Direction.SOUTH))) {
		        this.draw(g, "layer_1_wall_3", 80, 0);
		    }
		}
		else if (facing == Direction.WEST) {
			if (this.anyBlocked(x, y, new ExitCheck(0, 0, Direction.WEST))) {
		        this.draw(g, "layer_1_wall_3", 80, 0);
		    }
		}
		else if (facing == Direction.EAST) {
			if (this.anyBlocked(x, y, new ExitCheck(0, 0, Direction.EAST))) {
		        this.draw(g, "layer_1_wall_3", 80, 0);
		    }
		}

		this.draw(g, "layer_1_ceiling_3", 80, 0);

		g.dispose();

		return canvas;
	}

}
