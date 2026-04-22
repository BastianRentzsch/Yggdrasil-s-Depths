package commandSystem;

import game.Game;

public class InventoryCommand extends Command {
    @Override
    public void execute( Game game ) {
        game.getPlayer().getInventory().print();
    }
}