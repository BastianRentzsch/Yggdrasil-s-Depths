package commandSystem;

import game.Game;
import itemSystem.Item;

public class TakeCommand extends Command {
    private final String itemName;

    public TakeCommand( String itemName ) {
        this.itemName = itemName;
    }

    @Override
    public void execute( Game game ) {
        Item item = game.getPlayer()
                .getCurrentRoom()
                .takeItem( itemName );

        if ( item == null ) {
            System.out.println( "No such item here." );
            return;
        }

        game.getPlayer().pickUp( item );
    }
}