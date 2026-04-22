package commandSystem;

import dungeon.Direction;
import game.Game;

public class MoveCommandFactory implements CommandFactory {
    @Override
    public Command create( ParsedCommand input, Game game ) {
        if ( input.getArgs().isEmpty() ) {
            System.out.println( "Go where?" );
            return null;
        }

        String dirString = input.getArgs().getFirst().toLowerCase();
        Direction direction;

        try {
            direction = Direction.valueOf( dirString.toUpperCase() );
        } catch ( IllegalArgumentException e ) {
            Direction facing = game.getPlayer().getFacing();

            switch ( dirString ) {
                case "forward" -> direction = facing;
                case "backward" -> direction = facing.opposite();
                default -> {
                    System.out.println( "Unknown direction: " + dirString );
                    return null;
                }
            }
        }

        return new MoveCommand( direction );
    }
}