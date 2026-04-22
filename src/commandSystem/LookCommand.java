package commandSystem;

import game.Game;
import dungeon.Room;

// Command that lets the player look around the current room and see items
public class LookCommand extends Command {
    @Override
    public void execute( Game game ) {
        // Get the room where the player is currently located
        Room room = game.getPlayer().getCurrentRoom();

        System.out.println( "You look around..." );

        // Display items available in the room
        room.printItems();
    }
}