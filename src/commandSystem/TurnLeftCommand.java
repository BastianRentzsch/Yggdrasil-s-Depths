package commandSystem;

import game.Game;

public class TurnLeftCommand extends Command {
    @Override
    public void execute(Game game) {
        game.getPlayer().turnLeft();
    }
}