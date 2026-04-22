package commandSystem;

import dungeon.Direction;

public class MoveCommandFactory implements CommandFactory {
    @Override
    public Command create( ParsedCommand input ) {
        if ( input.getArgs().isEmpty() ) {
            System.out.println( "Go where?" );
            return null;
        }

        String dirString = input.getArgs().getFirst();

        try {
            Direction dir = Direction.valueOf( dirString.toUpperCase() );
            return new MoveCommand( dir );
        } catch ( IllegalArgumentException e ) {
            System.out.println( "Unknown direction: " + dirString );
            return null;
        }
    }
}