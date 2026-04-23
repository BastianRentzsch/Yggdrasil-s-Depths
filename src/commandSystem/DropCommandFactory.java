package commandSystem;

import game.Game;

// Creates DropCommand objects from parsed input
public class DropCommandFactory implements CommandFactory {
    public Command create( ParsedCommand input, Game game ) {
        // Check if the player provided an item name
        if ( input.args().isEmpty() ) {
            System.out.println( "Drop what?" );
            return null;
        }

        // Combine all arguments into a full item name (supports multi-word names)
        String itemName = String.join( " ", input.args() );

        // Create and return the DropCommand
        return new DropCommand( itemName );
    }
}