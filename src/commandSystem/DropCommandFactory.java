package commandSystem;

import game.Game;

public class DropCommandFactory implements CommandFactory {
    public Command create(ParsedCommand input, Game game ) {
        if ( input.getArgs().isEmpty() ) {
            System.out.println( "Drop what?" );
            return null;
        }

        String itemName = String.join( " ", input.getArgs() );
        return new DropCommand( itemName );
    }
}