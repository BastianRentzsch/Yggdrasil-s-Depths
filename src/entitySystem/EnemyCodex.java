package entitySystem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static itemSystem.ItemCodex.itemCodex;

public class EnemyCodex {
    public static final Map<String, Enemy> enemyCodex = new HashMap<>();

    static {
        enemyCodex.put( "Slime", new Enemy(
                "Slime",
                15,
                3,
                Arrays.asList(
                        itemCodex.get( "Potion" ),
                        itemCodex.get( "Potion" )
                ),
                """
        +------------------------------------------------------------------------------+
        |                                                                              |
        |                                  ██████████                                  |
        |                              ████░░░░░░░░░░████                              |
        |                            ██▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██                            |
        |                          ██▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██                          |
        |                          ██▒▒▒▒██▒▒▒▒▒▒▒▒▒▒██▒▒▒▒██                          |
        |                        ██▒▒▒▒▒▒██▒▒▒▒▒▒▒▒▒▒██▒▒▒▒▒▒██                        |
        |                        ██▒▒▒▒▒▒██▒▒▒▒▒▒▒▒▒▒██▒▒▒▒▒▒██                        |
        |                        ██▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██                        |
        |                        ██▒▒▒▒▒▒▒▒██▒▒▒▒▒▒██▒▒▒▒▒▒▒▒██                        |
        |                        ██▓▓▒▒▒▒▒▒▒▒██████▒▒▒▒▒▒▒▒▓▓██                        |
        |                          ██▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓██                          |
        |                            ██████████████████████                            |
        |                                                                              |
        |                             ____________________                             |
        |                            |%hhhhhhhhhhhhhhhhhhh|                            |
        |                             ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾                             |
        |                                     Slime                                    |
        |                                                                              |
        |                                                                              |
        +------------------------------------------------------------------------------+"""
        ) );
    }
}
