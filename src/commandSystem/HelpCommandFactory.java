package commandSystem;

import game.Game;

// Creates an HelpCommand (no arguments needed)
public class HelpCommandFactory implements CommandFactory {
    @Override
    public Command create(ParsedCommand input, Game game) {
        return new HelpCommand();
    }
}