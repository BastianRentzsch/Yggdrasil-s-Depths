// Copyright (c) 2026 Bastian Rentzsch

package commandSystem;

import game.Game;

// Creates a TurnLeftCommand (no arguments needed)
public class TurnLeftCommandFactory implements CommandFactory {
    @Override
    public Command create( ParsedCommand input, Game game ) {
        return new TurnLeftCommand();
    }
}