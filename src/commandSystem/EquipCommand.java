// Copyright (c) 2026 Bastian Rentzsch

package commandSystem;

import game.Game;
import itemSystem.Item;

// Command that equips an item from the player's inventory
public class EquipCommand extends Command {
    private final String itemName;

    public EquipCommand( String itemName ) {
        this.itemName = itemName;
    }

    @Override
    public void execute( Game game ) {
        var player = game.getPlayer();

        // Find the item in the player's inventory by name (case-insensitive)
        Item item = player.getInventory()
                .getItems()
                .stream()
                .filter( i -> i.getName().equalsIgnoreCase( this.itemName ) )
                .findFirst()
                .orElse( null );

        // If the item is not found, show an error message
        if ( item == null ) {
            System.out.println( "You don't have that item." );
            return;
        }

        // Equip the item
        player.equip( item );
    }
}