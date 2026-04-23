package commandSystem;

import game.Game;

// Creates an AttackCommand (no arguments needed)
public class AttackCommandFactory implements CommandFactory {
    public Command create( ParsedCommand input, Game game ) {
        return new AttackCommand();
    }
}