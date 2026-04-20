package dungeon;

import entitySystem.Entity;

public class Exit {
    private final Direction direction;
    private final Room target;
    private Side side;
//    private List<Condition>

    Exit(Direction direction, Room target, Side side ) {
        this.direction = direction;
        this.target = target;
        this.side = side;
    }

    Exit(Direction direction, Side side ) {
        this( direction, null, side );
    }

    public Exit(Direction direction, Room target ) {
        this( direction, target, null );
    }

    public boolean canPass(Entity entity) {
        return this.getTarget() != null;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public Room getTarget() {
        return this.target;
    }
}
