package commandSystem;

import java.util.Arrays;
import java.util.List;

public class CommandParser {

    private final CommandRegistry registry;

    public CommandParser( CommandRegistry registry ) {
        this.registry = registry;
    }

    public Command parse( String input ) {
        String trimmed = input.trim();

        if ( trimmed.isBlank() ) {
            return null;
        }

        String[] parts = trimmed.split( "\\s+" );
        String verb = parts[ 0 ].toLowerCase();
        List<String> args = Arrays.asList( parts ).subList( 1, parts.length );

        if ( !registry.supports( verb ) ) {
            System.out.println( "Unknown command: " + verb );
            return null;
        }

        return registry.get( verb ).create( new ParsedCommand( verb, args ) );
    }

}
