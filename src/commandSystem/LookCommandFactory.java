package commandSystem;

import game.Game;

public class LookCommandFactory implements CommandFactory {
    @Override
    public Command create( ParsedCommand input, Game game ) {
        return new LookCommand();
    }
}