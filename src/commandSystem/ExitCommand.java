// Copyright (c) 2026 Bastian Rentzsch

package commandSystem;

import game.Game;

// Command that allows the player exit the game
public class ExitCommand extends Command {
    @Override
    public void execute( Game game ) {
        System.out.println( "Exiting game..." );
        game.stop();
    }

    // Indicates this command is allowed during combat
    @Override
    public boolean allowedInCombat() {
        return true;
    }
}