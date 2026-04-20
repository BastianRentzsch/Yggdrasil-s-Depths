package commandSystem;

import game.Game;
import itemSystem.Item;

public class EquipCommand extends Command {

    private final String itemName;

    public EquipCommand( String itemName ) {
        this.itemName = itemName;
    }

    @Override
    public void execute( Game game ) {

        var player = game.getPlayer();

        Item item = player.getInventory()
                .getItems()
                .stream()
                .filter( i -> i.getName().equalsIgnoreCase( itemName ) )
                .findFirst()
                .orElse( null );

        if ( item == null ) {
            System.out.println( "You don't have that item." );
            return;
        }

        player.equip( item );
    }
}
