package commandSystem;

import game.Game;

public class InventoryCommandFactory implements CommandFactory {
    public Command create( ParsedCommand input, Game game ) {
        return new InventoryCommand();
    }
}