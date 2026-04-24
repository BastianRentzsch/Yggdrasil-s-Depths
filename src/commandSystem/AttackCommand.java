// Copyright (c) 2026 Bastian Rentzsch

package commandSystem;

import entitySystem.Enemy;
import entitySystem.Player;
import game.Game;
import itemSystem.EquipmentSlot;
import itemSystem.Weapon;

// Command for attacking an enemy the player is fighting
public class AttackCommand extends Command {
    @Override
    public void execute( Game game ) {
        // Check if the player is currently in combat
        if ( !game.isInCombat() ) {
            System.out.println( "There is nothing to attack." );
            return;
        }

        // Get references to the player and current enemy
        Player player = game.getPlayer();
        Enemy enemy = game.getCurrentEnemy();

        // Retrieve the player's equipped weapon
        Weapon weapon = ( Weapon ) player.getEquipped( EquipmentSlot.WEAPON );

        // Ensure the player has a weapon equipped
        if ( weapon == null ) {
            System.out.println( "You have no weapon equipped!" );
            return;
        }

        // Apply damage to the enemy based on weapon stats
        enemy.takeDamage( weapon.getDamage() );
        System.out.println( "You hit " + enemy.getName() );

        // Check if the enemy has been defeated
        if ( !enemy.isAlive() ) {
            System.out.println( enemy.getName() + " defeated!" );

            // Transfer dropped loot from enemy to player
            enemy.dropLoot().forEach( player::pickUp );

            // Clear the current enemy from the game
            game.setCurrentEnemy( null );
            return;
        }

        // Enemy performs a counterattack on the player
        enemy.attack( player );

        // Check if the player died from the counterattack
        if ( !player.isAlive() ) {
            System.out.println( "You died!" );

            // Terminate the game when the player dies
            System.exit( 0 );
        }
    }

    // Indicates this command is allowed during combat
    @Override
    public boolean allowedInCombat() {
        return true;
    }
}