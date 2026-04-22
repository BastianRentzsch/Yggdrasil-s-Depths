import commandSystem.*;
import dungeon.Dungeon;
import dungeon.Room;
import entitySystem.Player;
import game.Game;
import utils.ConsoleUtils;

void main() {
    // Dungeon
    List<Room> dungeon = Dungeon.createDungeon();

    // Player
    Player player = new Player( "test", dungeon.get( 15 ) );

    // Commands
    CommandParser parser = CommandConfig.createParser();

    // Game
    Game game = new Game( dungeon, player, parser );

    // Start
//    ConsoleUtils.clearConsole();
    game.start();
}