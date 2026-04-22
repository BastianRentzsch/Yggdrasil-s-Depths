package commandSystem;

import game.Game;
import itemSystem.Item;

public class DropCommand extends Command {
    private final String itemName;

    public DropCommand( String itemName ) {
        this.itemName = itemName;
    }

    @Override
    public void execute( Game game ) {
        // Get Item from Inventory
        Item item = game.getPlayer()
                .getInventory()
                .getItem( itemName );

        // Look if Item existed
        if ( item == null ) {
            System.out.println( "You do not have " + itemName + " so it cannot be dropped." );
            return;
        }

        // Drop Item
        game.getPlayer().drop( item );
    }
}