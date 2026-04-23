package commandSystem;

import game.Game;

// Interface for creating Command objects from a parsed input and the game context
public interface CommandFactory {
    Command create( ParsedCommand input, Game game );
}