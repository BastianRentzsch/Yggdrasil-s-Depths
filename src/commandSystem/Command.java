// Copyright (c) 2026 Bastian Rentzsch

package commandSystem;

import game.Game;

// Abstract base class for all commands; each command must implement execute() to act on the game
public abstract class Command {
    public abstract void execute( Game game );

    // Indicates whether the command can be used during combat (default is false)
    public boolean allowedInCombat() {
        return false;
    }
}