package commandSystem;

import game.Game;

// Creates a LookCommand (no arguments needed)
public class LookCommandFactory implements CommandFactory {
    @Override
    public Command create( ParsedCommand input, Game game ) {
        return new LookCommand();
    }
}