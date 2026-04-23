package commandSystem;

import game.Game;
import itemSystem.Item;

// Command that lets the player pick up an item from the current room
public class TakeCommand extends Command {
    private final String itemName;

    public TakeCommand( String itemName ) {
        this.itemName = itemName;
    }

    @Override
    public void execute( Game game ) {
        // Try to take the item from the current room
        Item item = game.getPlayer()
                .getCurrentRoom()
                .takeItem( this.itemName );

        // If the item is not found, show a message
        if ( item == null ) {
            System.out.println( "No such item here." );
            return;
        }

        // Add the item to the player's inventory
        game.getPlayer().pickUp( item );
    }
}