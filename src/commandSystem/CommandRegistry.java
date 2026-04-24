// Copyright (c) 2026 Bastian Rentzsch

package commandSystem;

import java.util.HashMap;
import java.util.Map;

// Stores and manages available command factories mapped by command name
public class CommandRegistry {
    private final Map<String, CommandFactory> factories = new HashMap<>();

    // Registers a command name with its corresponding factory
    public void register( String name, CommandFactory factory ) {
        this.factories.put( name, factory );
    }

    // Retrieves the factory for a given command name
    public CommandFactory get( String name ) {
        return this.factories.get (name );
    }

    // Checks whether a command is supported (registered)
    public boolean supports( String name ) {
        return this.factories.containsKey( name );
    }
}