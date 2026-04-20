package commandSystem;

public class CommandConfig {
    public static CommandParser createParser() {
        CommandRegistry registry = new CommandRegistry();

        registry.register( "go", new MoveCommandFactory() );
        registry.register( "move", new MoveCommandFactory() );
        registry.register( "inventory", new InventoryCommandFactory() );
        registry.register( "take", new TakeCommandFactory() );
        registry.register( "pickup", new TakeCommandFactory() );
        registry.register( "grab", new TakeCommandFactory() );
        registry.register( "look", new LookCommandFactory() );
        registry.register( "inspect", new LookCommandFactory() );
        registry.register( "equip", new EquipCommandFactory() );
        registry.register( "wear", new EquipCommandFactory() );

       return new CommandParser( registry );
    }
}
