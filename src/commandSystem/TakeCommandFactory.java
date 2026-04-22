package commandSystem;

import game.Game;

public class TakeCommandFactory  implements CommandFactory {
    @Override
    public Command create( ParsedCommand input, Game game ) {
        if ( input.getArgs().isEmpty() ) {
            System.out.println( "Take what?" );
            return null;
        }

        String itemName = String.join( " ", input.getArgs() );
        return new TakeCommand( itemName );
    }
}