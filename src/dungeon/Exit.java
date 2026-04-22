package dungeon;

// Represents an exit from a room, containing a direction and the target room it leads to
public record Exit(Direction direction, Room target) {
}