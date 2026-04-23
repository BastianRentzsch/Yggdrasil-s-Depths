package itemSystem;

import java.util.HashMap;
import java.util.Map;

// A central registry (codex) of all predefined items in the game
public class ItemCodex {
    public static final Map<String, Item> itemCodex = new HashMap<>();

    static {
        // Weapons
        itemCodex.put( "Old sword", new Weapon( "Old sword", "old rusted iron sword", 2 ) );
        itemCodex.put( "Sword", new Weapon( "Sword", "Plain iron sword", 3 ) );

        // Armor
        itemCodex.put( "Leather Armor", new Armor( "Leather Armor", "Plain armor out of leather", 2 ) );
        itemCodex.put( "Iron Armor", new Armor( "Iron Armor", "Plain armor out of iron, better than leather", 3 ) );

        // Consumables
        itemCodex.put( "Potion", new Consumable( "Potion", "Heals 10 HP", 10 ) );
        itemCodex.put( "Hi-potion", new Consumable( "Hi-potion", "Heals 20 HP", 20 ) );

        itemCodex.put( "Potato seed", new Consumable( "Potato seed", "A potato seed", 0 ) );
        itemCodex.put( "Tomato seed", new Consumable( "Tomato seed", "A tomato seed", 0 ) );
        itemCodex.put( "Lightbulb", new Consumable( "Lightbulb", "A lightbulb", 0 ) );
    }
}