// Copyright (c) 2026 Bastian Rentzsch

package commandSystem;

import game.Game;

// Creates EquipCommand objects from parsed input
public class EquipCommandFactory implements CommandFactory {
    @Override
    public Command create( ParsedCommand input, Game game ) {
        // Check if the player provided an item name
        if ( input.args().isEmpty() ) {
            System.out.println( "Equip what?" );
            return null;
        }

        // Combine arguments into a full item name (supports multi-word names)
        String itemName = String.join( " ", input.args() );

        // Create and return the EquipCommand
        return new EquipCommand( itemName );
    }
}