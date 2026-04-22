package commandSystem;

import game.Game;

public class EquipCommandFactory implements CommandFactory {
    @Override
    public Command create( ParsedCommand input, Game game ) {
        if ( input.getArgs().isEmpty() ) {
            System.out.println( "Equip what?" );
            return null;
        }

        String itemName = String.join( " ", input.getArgs() );
        return new EquipCommand( itemName );
    }
}