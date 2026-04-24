// Copyright (c) 2026 Bastian Rentzsch

package commandSystem;

import game.Game;

import java.util.Arrays;
import java.util.List;

// Parses a user input string into a Command object using a registry of known commands
public class CommandParser {
    private final CommandRegistry registry;

    public CommandParser( CommandRegistry registry ) {
        this.registry = registry;
    }

    public Command parse( String input, Game game ) {
        String trimmed = input.trim();

        // Return null if input is empty
        if ( trimmed.isBlank() ) {
            return null;
        }

        // Split input into words (verb + arguments)
        String[] parts = trimmed.split( "\\s+" );
        String verb = parts[ 0 ].toLowerCase();
        List<String> args = Arrays.asList( parts ).subList( 1, parts.length );

        // Check if the command exist
        if ( !this.registry.supports( verb ) ) {
            System.out.println( "Unknown command: " + verb );
            return null;
        }

        // Create and return the corresponding Command using its factory
        return this.registry.get( verb ).create( new ParsedCommand( verb, args ), game );
    }
}