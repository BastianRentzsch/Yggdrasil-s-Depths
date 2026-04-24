// Copyright (c) 2026 Bastian Rentzsch

package commandSystem;

import game.Game;

// Creates UseCommand objects from parsed input
public class UseCommandFactory implements CommandFactory {
    @Override
    public Command create( ParsedCommand input, Game game ) {
        if ( input.args().isEmpty() ) {
            System.out.println( "Use what?" );
            return null;
        }

        return new UseCommand( String.join( " ", input.args() ) );
    }
}