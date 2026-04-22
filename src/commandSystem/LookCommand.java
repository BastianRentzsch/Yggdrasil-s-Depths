package commandSystem;

import game.Game;
import dungeon.Room;

public class LookCommand extends Command {
    @Override
    public void execute( Game game ) {
        Room room = game.getPlayer().getCurrentRoom();

        System.out.println( "You look around..." );
        room.printItems();
    }
}