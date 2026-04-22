package commandSystem;

import game.Game;
import itemSystem.Item;

// Command that removes an item from the player's inventory and drops it
public class DropCommand extends Command {
    private final String itemName;

    public DropCommand( String itemName ) {
        this.itemName = itemName;
    }

    @Override
    public void execute( Game game ) {
        // Get the item from the player's inventory
        Item item = game.getPlayer()
                .getInventory()
                .getItem( itemName );

        // If the item is not found, inform the player
        if ( item == null ) {
            System.out.println( "You do not have " + itemName + " so it cannot be dropped." );
            return;
        }

        // Drop the item from the inventory
        game.getPlayer().drop( item );
    }
}