// Copyright (c) 2026 Bastian Rentzsch

package commandSystem;

import entitySystem.Enemy;
import entitySystem.Player;
import game.Game;
import itemSystem.Consumable;
import itemSystem.Item;

// // Command that uses an item from the player's inventory and removes it
public class UseCommand extends Command {
    private final String itemName;

    public UseCommand( String itemName ) {
        this.itemName = itemName;
    }

    @Override
    public void execute( Game game ) {
        // Get reference to the player
        Player player = game.getPlayer();

        // Retrieve the item from the player's inventory
        Item item = player.getInventory().getItem( this.itemName );

        // Check if the item exists in the inventory
        if ( item == null ) {
            System.out.println( "You don't have that item." );
            return;
        }

        // Ensure the item is a consumable that can be used
        if ( !( item instanceof Consumable usable ) ) {
            System.out.println( "You can't use that." );
            return;
        }

        // Apply the item's effect to the player
        usable.use( player );

        // Remove the item from the inventory after use
        player.getInventory().remove( item );

        // Inform the player about the used item
        System.out.println( "You used " + item.getName() );

        // Allow enemy to act if the player is in combat
        if ( game.isInCombat() ) {
            // Get the current enemy
            Enemy enemy = game.getCurrentEnemy();

            // Enemy attacks the player
            enemy.attack( player );

            // Check if the player died from the attack
            if ( !player.isAlive() ) {
                System.out.println( "You died!" );

                // Terminate the game if the player dies
                System.exit( 0 );
            }
        }
    }

    // Indicates this command is allowed during combat
    @Override
    public boolean allowedInCombat() {
        return true;
    }
}