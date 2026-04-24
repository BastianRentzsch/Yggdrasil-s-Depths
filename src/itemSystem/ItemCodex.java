// Copyright (c) 2026 Bastian Rentzsch

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
        itemCodex.put( "Potion", new Consumable( "Potion", "Heals 10 HP", new HealEffect( 10 ) ) );
        itemCodex.put( "Hi-potion", new Consumable( "Hi-potion", "Heals 20 HP", new HealEffect( 20 ) ) );

        itemCodex.put( "Volt Jar", new Consumable( "Volt Jar", "A Jar of bottled lightning", new DamageEffect( 15, "lightning" ) ) );

        // Items
        itemCodex.put( "Potato seed", new Item( "Potato seed", "A potato seed" ) );
        itemCodex.put( "Tomato seed", new Item( "Tomato seed", "A tomato seed" ) );
        itemCodex.put( "Lightbulb", new Item( "Lightbulb", "A lightbulb" ) );
    }
}