// Copyright (c) 2026 Bastian Rentzsch

package commandSystem;

import game.Game;

// Command that shows the player all allowed commands
public class HelpCommand extends Command {
    @Override
    public void execute( Game game ) {
        System.out.println( "Available commands:" );

        System.out.println( "- go / move <direction>" );
        System.out.println( "- look / inspect" );
        System.out.println( "- take / pickup / grab <item>" );
        System.out.println( "- drop <item>" );
        System.out.println( "- inventory" );
        System.out.println( "- equip / wear <item>" );
        System.out.println( "- attack / hit" );
        System.out.println( "- use <item>" );
        System.out.println( "- flee / run" );
        System.out.println( "- left / right" );
        System.out.println( "- help / ?" );
        System.out.println( "- exit / quit" );
    }

    // Indicates this command is allowed during combat
    @Override
    public boolean allowedInCombat() {
        return true;
    }
}