package game;

import commandSystem.Command;
import commandSystem.CommandParser;
import dungeon.Room;
import entitySystem.Player;
import utils.ConsoleUtils;

import java.util.List;
import java.util.Scanner;

// Main game loop and core runtime controller
public class Game {
    private List<Room> rooms;
    private final Player player;
    private final CommandParser parser;

    public Game( List<Room> rooms, Player player, CommandParser parser ) {
        this.rooms = rooms;
        this.player = player;
        this.parser = parser;
    }

    // Returns the player instance
    public Player getPlayer() {
        return this.player;
    }

    // Starts the game loop and handles user input
    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println( "game.Game started. Type commands (e.g., 'go north')." );

        while ( true ) {
            // Show player direction and current view
            System.out.println("Direction: " + this.player.getFacing() );
            this.player.look();

            // Read user input
            System.out.print( "> " );
            String input = scanner.nextLine();

            // Parse and execute command
            Command command = parser.parse( input, this );
            if ( command != null ) {
                command.execute( this );
            }

            // Pause before clearing screen
            System.out.println("\nPress ENTER to continue...");
            scanner.nextLine();

            ConsoleUtils.clearConsole();
        }
    }
}