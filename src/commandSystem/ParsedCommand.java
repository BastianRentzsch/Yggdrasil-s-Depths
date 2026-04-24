// Copyright (c) 2026 Bastian Rentzsch

package commandSystem;

import java.util.List;

// A record that stores a command with its name (verb) and a list of arguments (args)
public record ParsedCommand( String verb, List<String> args ) {
}