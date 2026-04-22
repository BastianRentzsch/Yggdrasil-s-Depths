package dungeon;

import entitySystem.Entity;

public class Exit {
    private final Direction direction;
    private final Room target;
//    private List<Condition>

    public Exit(Direction direction, Room target ) {
        this.direction = direction;
        this.target = target;
    }

    public boolean canPass( Entity entity ) {
        return this.getTarget() != null;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public Room getTarget() {
        return this.target;
    }
}