package commandSystem;

import game.Game;

// Command that turns the player to the left
public class TurnLeftCommand extends Command {
    @Override
    public void execute( Game game ) {
        game.getPlayer().turnLeft();
    }
}