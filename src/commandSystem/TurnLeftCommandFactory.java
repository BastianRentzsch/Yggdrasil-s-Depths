package commandSystem;

public class TurnLeftCommandFactory implements CommandFactory {
    @Override
    public Command create( ParsedCommand input ) {
        return new TurnLeftCommand();
    }
}