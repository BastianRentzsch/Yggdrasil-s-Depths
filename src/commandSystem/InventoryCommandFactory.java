package commandSystem;

public class InventoryCommandFactory implements CommandFactory {
    public Command create( ParsedCommand input ) {
        return new InventoryCommand();
    }
}
