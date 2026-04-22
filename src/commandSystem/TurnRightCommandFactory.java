package commandSystem;

public class TurnRightCommandFactory implements CommandFactory {
    @Override
    public Command create( ParsedCommand input ) {
        return new TurnRightCommand();
    }
}