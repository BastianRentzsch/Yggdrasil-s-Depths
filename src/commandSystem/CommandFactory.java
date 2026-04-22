package commandSystem;

public interface CommandFactory {
    Command create(ParsedCommand input);
}