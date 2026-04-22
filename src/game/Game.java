package game;

import commandSystem.Command;
import commandSystem.CommandParser;
import dungeon.Room;
import entitySystem.Player;
import utils.ConsoleUtils;

import java.util.List;
import java.util.Scanner;

public class Game {
    private List<Room> rooms;
    private final Player player;
    private final CommandParser parser;

    public Game( List<Room> rooms, Player player, CommandParser parser ) {
        this.rooms = rooms;
        this.player = player;
        this.parser = parser;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println( "game.Game started. Type commands (e.g., 'go north')." );

        while ( true ) {
            System.out.println("Direction: " + this.player.getFacing() );
            this.player.look();
            System.out.print( "> " );
            String input = scanner.nextLine();

            Command command = parser.parse( input, this );

            if ( command != null ) {
                command.execute( this );
            }

            System.out.println("\nPress ENTER to continue...");
            scanner.nextLine();
            ConsoleUtils.clearConsole();
        }
    }
}