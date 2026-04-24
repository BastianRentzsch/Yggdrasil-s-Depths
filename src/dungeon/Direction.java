// Copyright (c) 2026 Bastian Rentzsch

package dungeon;

// Represents the four possible movement directions in the dungeon
public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    // Returns the opposite direction (e.g., NORTH -> SOUTH)
    public Direction opposite() {
        return switch ( this ) {
            case NORTH -> SOUTH;
            case SOUTH -> NORTH;
            case EAST  -> WEST;
            case WEST  -> EAST;
        };
    }
}