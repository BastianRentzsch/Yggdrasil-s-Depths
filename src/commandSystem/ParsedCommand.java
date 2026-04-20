package commandSystem;

import java.util.List;

public class ParsedCommand {
    private final String verb;
    private final List<String> args;

    public ParsedCommand( String verb, List<String> args ) {
        this.verb = verb;
        this.args = args;
    }

    public String getVerb() {
        return verb;
    }

    public List<String> getArgs() {
        return args;
    }
}
