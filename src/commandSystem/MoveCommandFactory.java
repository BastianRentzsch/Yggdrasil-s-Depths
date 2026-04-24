// Copyright (c) 2026 Bastian Rentzsch

package commandSystem;

import dungeon.Direction;
import game.Game;

// Creates MoveCommand objects from parsed input and converts text directions into game directions
public class MoveCommandFactory implements CommandFactory {
    @Override
    public Command create( ParsedCommand input, Game game ) {
        // Check if a direction was provided
        if ( input.args().isEmpty() ) {
            System.out.println( "Go where?" );
            return null;
        }

        String dirString = input.args().getFirst().toLowerCase();
        Direction direction;

        try {
            // Try to match a direct direction (NORTH, EAST, SOUTH, WEST)
            direction = Direction.valueOf( dirString.toUpperCase() );
        } catch ( IllegalArgumentException e ) {
            // If not a direct direction, interpret relative movement
            Direction facing = game.getPlayer().getFacing();

            switch ( dirString ) {
                case "forward" -> direction = facing;
                case "backward" -> direction = facing.opposite();
                default -> {
                    System.out.println( "Unknown direction: " + dirString );
                    return null;
                }
            }
        }

        // Create and return the movement command
        return new MoveCommand( direction );
    }
}