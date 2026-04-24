// Copyright (c) 2026 Bastian Rentzsch

package commandSystem;

import dungeon.Direction;
import game.Game;

// Command that moves the player in a given direction
public class MoveCommand extends Command {
    private final Direction direction;

    public MoveCommand( Direction direction ) {
        this.direction = direction;
    }

    @Override
    public void execute( Game game ) {
        // Move the player in the specified direction
        game.getPlayer().move( this.direction, game );
    }
}