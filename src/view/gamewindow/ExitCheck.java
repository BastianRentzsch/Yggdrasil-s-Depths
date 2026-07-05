// Copyright (c) 2026 Bastian Rentzsch

package view.gamewindow;

import model.dungeon.Direction;

/**
 * Immutable data record that represents an exit check for a dungeon tile.
 * <p>
 * It stores the relative coordinate offset and the corresponding
 * {@link Direction} that should be checked for an exit.
 * </p>
 *
 * @param dx  the horizontal offset relative to the current position
 * @param dy  the vertical offset relative to the current position
 * @param dir the direction associated with the exit check
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
record ExitCheck(int dx, int dy, Direction dir) {
}
