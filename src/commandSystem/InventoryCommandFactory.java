// Copyright (c) 2026 Bastian Rentzsch

package commandSystem;

import game.Game;

// Creates an InventoryCommand (no arguments needed)
public class InventoryCommandFactory implements CommandFactory {
    public Command create( ParsedCommand input, Game game ) {
        return new InventoryCommand();
    }
}