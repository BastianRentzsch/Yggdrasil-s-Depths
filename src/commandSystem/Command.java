package commandSystem;

import game.Game;


// Abstract base class for all commands; each command must implement execute() to act on the game
public abstract class Command {
    public abstract void execute( Game game );
}