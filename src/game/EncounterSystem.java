package game;

import entitySystem.Enemy;
import static entitySystem.EnemyCodex.enemyCodex;
import entitySystem.Player;

import java.util.List;
import java.util.Random;

// Handles random enemy encounters by deciding if one occurs and starting combat with a randomly selected enemy.
public class EncounterSystem {
    private static final Random random = new Random();
    // Chance (e.g. 30%)
    private static final double encounterChance = 0.3;

    // Checks if a random encounter should occur after player movement
    public static void checkForEncounter( Player player, Game game ) {
        // Roll chance to determine if an encounter happens
        if ( random.nextDouble() > encounterChance ) return;

        // Select a random enemy and start combat
        Enemy enemy = getRandomEnemy();
        startCombat( player, game, enemy );
    }

    // Selects and returns a random enemy from the codex
    private static Enemy getRandomEnemy() {
        // Create a list of all available enemy templates
        List<Enemy> enemies = List.copyOf( enemyCodex.values() );

        // Pick a random enemy template and return a copy
        Enemy template = enemies.get( random.nextInt( enemies.size() ) );
        return template.copy();
    }

    // Initializes combat with the selected enemy
    private static void startCombat( Player player, Game game, Enemy enemy ) {
        // Inform the player about the encounter
        System.out.println( "A wild " + enemy.getName() + " appears!" );

        // Set the enemy as the current combat target
        game.setCurrentEnemy( enemy );
    }
}