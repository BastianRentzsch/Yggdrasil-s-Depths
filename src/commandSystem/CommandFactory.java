package commandSystem;

import game.Game;

public interface CommandFactory {
    Command create(ParsedCommand input, Game game );
}