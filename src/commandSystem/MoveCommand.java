package commandSystem;

import dungeon.Direction;
import game.Game;

public class MoveCommand extends Command {
    private final Direction direction;

    public MoveCommand(Direction direction ) {
        this.direction = direction;
    }

    @Override
    public void execute( Game game ) {
        game.getPlayer().move( direction );
    }
}