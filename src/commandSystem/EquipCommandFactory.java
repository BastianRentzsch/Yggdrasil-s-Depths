package commandSystem;

public class EquipCommandFactory implements CommandFactory {

    @Override
    public Command create( ParsedCommand input ) {
        if ( input.getArgs().isEmpty() ) {
            System.out.println( "Equip what?" );
            return null;
        }

        String itemName = String.join( " ", input.getArgs() );
        return new EquipCommand( itemName );
    }
}
