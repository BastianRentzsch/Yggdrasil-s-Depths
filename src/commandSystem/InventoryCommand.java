// Copyright (c) 2026 Bastian Rentzsch

package commandSystem;

import game.Game;

// Command that displays the player's inventory
public class InventoryCommand extends Command {
    @Override
    public void execute( Game game ) {
        // Show the player's inventory contents
        game.getPlayer().getInventory().print();
    }

    // Indicates this command is allowed during combat
    @Override
    public boolean allowedInCombat() {
        return true;
    }
}