// Copyright (c) 2026 Bastian Rentzsch

package commandSystem;

import game.Game;

// Creates a TurnRightCommand (no arguments needed)
public class TurnRightCommandFactory implements CommandFactory {
    @Override
    public Command create( ParsedCommand input, Game game ) {
        return new TurnRightCommand();
    }
}