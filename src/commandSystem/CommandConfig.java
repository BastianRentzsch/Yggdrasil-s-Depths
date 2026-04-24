// Copyright (c) 2026 Bastian Rentzsch

package commandSystem;

// Configures and returns a CommandParser with all available commands registered
public class CommandConfig {
    public static CommandParser createParser() {
        CommandRegistry registry = new CommandRegistry();

        // Register all valid commands and their corresponding factories
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
        registry.register( "left", new TurnLeftCommandFactory() );
        registry.register( "right", new TurnRightCommandFactory() );
        registry.register( "drop", new DropCommandFactory() );
        registry.register( "attack", new AttackCommandFactory() );
        registry.register( "hit", new AttackCommandFactory() );
        registry.register( "use", new UseCommandFactory() );
        registry.register( "flee", new FleeCommandFactory() );
        registry.register( "run", new FleeCommandFactory() );
        registry.register( "exit", new ExitCommandFactory() );
        registry.register( "quit", new ExitCommandFactory() );
        registry.register( "help", new HelpCommandFactory() );
        registry.register( "?", new HelpCommandFactory() );

       return new CommandParser( registry );
    }
}