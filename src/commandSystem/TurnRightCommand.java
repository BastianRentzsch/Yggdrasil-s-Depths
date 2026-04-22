package commandSystem;

import game.Game;

// Command that turns the player to the right
public class TurnRightCommand extends Command {
    @Override
    public void execute(Game game) {
        game.getPlayer().turnRight();
    }
}