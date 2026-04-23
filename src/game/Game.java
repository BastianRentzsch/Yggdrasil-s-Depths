package game;

import commandSystem.Command;
import commandSystem.CommandParser;
import dungeon.Room;
import entitySystem.Enemy;
import entitySystem.Player;
import utils.ConsoleUtils;

import java.util.List;
import java.util.Scanner;

// Main game loop and core runtime controller
public class Game {
    private List<Room> rooms;
    private final Player player;
    private final CommandParser parser;
    private Enemy currentEnemy;
    private boolean running = true;

    public Game( List<Room> rooms, Player player, CommandParser parser ) {
        this.rooms = rooms;
        this.player = player;
        this.parser = parser;
    }

    // Returns the current enemy in combat
    public Enemy getCurrentEnemy() {
        return currentEnemy;
    }

    // Sets the current enemy for combat
    public void setCurrentEnemy( Enemy enemy ) {
        this.currentEnemy = enemy;
    }

    // Returns the player instance
    public Player getPlayer() {
        return this.player;
    }

    // Checks whether the player is currently in combat
    public boolean isInCombat() {
        return currentEnemy != null && currentEnemy.isAlive();
    }

    // Starts the game loop and handles user input
    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println( "game.Game started. Type commands (e.g., 'go north')." );

        while ( isRunning() ) {
            // Display player status including direction, health, attack, and defense
            System.out.println("Direction: " + this.player.getFacing() + " Health: |" + this.player.getHealth() + "| Attack: "
                    + this.player.getDamage() + " Defense: "
                    + this.player.getDefense() );

            // Show enemy art if in combat, otherwise show room view
            if ( isInCombat() ) {
                this.currentEnemy.showArt();
            }
            else {
                this.player.look();
            }

            // Prompt the user for input
            System.out.print( "> " );
            String input = scanner.nextLine();

            // Parse the input into a command
            Command command = parser.parse( input, this );

            // Prevent non-combat commands during combat
            if ( isInCombat() && !command.allowedInCombat() ) {
                System.out.println( "You are in combat! Use 'attack', 'use', or 'flee'." );
            }
            // Execute the command if it is valid
            else if ( command != null ) {
                command.execute( this );
            }

            // Pause to allow the player to read output
            System.out.println("\nPress ENTER to continue...");
            scanner.nextLine();

            // Clear the console for the next frame
            ConsoleUtils.clearConsole();
        }
    }

    // Stops the game
    public void stop() {
        running = false;
    }

    // Returns the state of the game (running or stopped)
    public boolean isRunning() {
        return running;
    }
}