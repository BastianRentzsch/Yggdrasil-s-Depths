package commandSystem;

import game.Game;

public class TurnLeftCommandFactory implements CommandFactory {
    @Override
    public Command create( ParsedCommand input, Game game ) {
        return new TurnLeftCommand();
    }
}