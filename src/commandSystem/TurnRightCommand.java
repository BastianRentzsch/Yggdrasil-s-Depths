package commandSystem;

import game.Game;

public class TurnRightCommand extends Command {
    @Override
    public void execute(Game game) {
        game.getPlayer().turnRight();
    }
}