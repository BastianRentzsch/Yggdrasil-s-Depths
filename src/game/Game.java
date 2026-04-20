package game;


import commandSystem.Command;
import commandSystem.CommandParser;
import commandSystem.CommandRegistry;
import commandSystem.MoveCommandFactory;
import dungeon.Room;
import entitySystem.Player;

import java.util.List;
import java.util.Scanner;

public class Game {
    private List<Room> rooms;
    private Player player;
    private final CommandParser parser;

    public Game(List<Room> rooms, Player player, CommandParser parser ) {
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
            System.out.print( "> " );
            String input = scanner.nextLine();

            Command command = parser.parse( input );

            if ( command != null ) {
                command.execute( this );
            }
        }
    }
}
