package commandSystem;

import entitySystem.Enemy;
import entitySystem.Player;
import game.Game;

import java.util.Random;

// Command that allows the player to flee an encounter
public class FleeCommand extends Command {
    // Random generator used to determine flee success
    private static final Random random = new Random();

    @Override
    public void execute( Game game ) {
        // Check if the player is currently in combat
        if ( !game.isInCombat() ) {
            System.out.println( "There is nothing to flee from." );
            return;
        }

        // Get references to the player and current enemy
        Player player = game.getPlayer();
        Enemy enemy = game.getCurrentEnemy();

        // Define the probability of successfully fleeing
        double fleeChance = 0.6;

        // Attempt to flee based on random chance
        if ( random.nextDouble() < fleeChance ) {
            System.out.println( "You successfully fled!" );

            // Remove the current enemy from combat
            game.setCurrentEnemy( null );
            return;
        }

        // Inform the player that fleeing failed
        System.out.println( "You failed to flee!" );

        // Enemy attacks the player as punishment for failing to flee
        enemy.attack( player );

        // Check if the player died from the attack
        if ( !player.isAlive() ) {
            System.out.println( "You died!" );

            // Terminate the game if the player dies
            System.exit( 0 );
        }
    }

    // Indicates this command is allowed during combat
    @Override
    public boolean allowedInCombat() {
        return true;
    }
}