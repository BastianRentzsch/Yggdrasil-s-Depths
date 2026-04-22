package commandSystem;

public class LookCommandFactory implements CommandFactory {
    @Override
    public Command create( ParsedCommand input ) {
        return new LookCommand();
    }
}