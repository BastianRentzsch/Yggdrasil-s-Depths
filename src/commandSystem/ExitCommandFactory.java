package commandSystem;

import game.Game;

// Creates an ExitCommand (no arguments needed)
public class ExitCommandFactory implements CommandFactory {
    @Override
    public Command create( ParsedCommand input, Game game ) {
        return new ExitCommand();
    }
}