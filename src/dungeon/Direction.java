package dungeon;

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public Direction opposite() {
        return switch ( this ) {
            case NORTH -> SOUTH;
            case SOUTH -> NORTH;
            case EAST  -> WEST;
            case WEST  -> EAST;
        };
    }
}