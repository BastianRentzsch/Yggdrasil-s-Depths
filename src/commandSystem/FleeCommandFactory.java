package commandSystem;

import game.Game;

// Creates an FleeCommand (no arguments needed)
public class FleeCommandFactory implements CommandFactory {
    @Override
    public Command create( ParsedCommand input, Game game ) {
        return new FleeCommand();
    }
}