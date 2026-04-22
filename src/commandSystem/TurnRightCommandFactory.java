package commandSystem;

import game.Game;

public class TurnRightCommandFactory implements CommandFactory {
    @Override
    public Command create( ParsedCommand input, Game game ) {
        return new TurnRightCommand();
    }
}