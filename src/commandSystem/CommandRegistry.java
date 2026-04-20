package commandSystem;

import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {

    private final Map<String, CommandFactory> factories = new HashMap<>();

    public void register( String name, CommandFactory factory ) {
        factories.put( name, factory );
    }

    public CommandFactory get( String name ) {
        return factories.get (name );
    }

    public boolean supports( String name ) {
        return factories.containsKey( name );
    }
}