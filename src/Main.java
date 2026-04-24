// Copyright (c) 2026 Bastian Rentzsch

import commandSystem.*;
import dungeon.Dungeon;
import dungeon.Room;
import entitySystem.Player;
import game.Game;
import utils.ConsoleUtils;

// Entry point of the game: sets up the dungeon, player, and command system, then starts the game loop
void main() {
    // Create dungeon layout
    List<Room> dungeon = Dungeon.createDungeon();

    // Create player starting in room 15
    Player player = new Player( "test", 15, dungeon.get( 15 ) );

    // Create command parser with all registered commands
    CommandParser parser = CommandConfig.createParser();

    // Create game instance
    Game game = new Game( dungeon, player, parser );

    // Clear console and start game
    ConsoleUtils.clearConsole();
    game.start();
}