// Copyright (c) 2026 Bastian Rentzsch

package entitySystem;

import itemSystem.Armor;
import itemSystem.EquipmentSlot;
import itemSystem.Item;

import java.util.List;

// Represents an enemy entity that can attack the player and drop loot
public class Enemy extends Entity {
    private final int attackPower;
    private final List<Item> loot;
    private final String art;

    public Enemy( String name, int health, int attackPower,  List<Item> loot, String art ) {
        super( name, health );
        this.attackPower = attackPower;
        this.loot = loot;
        this.art = art;
    }

    // Displays the enemy's ASCII art with a dynamic health bar
    public void showArt() {
        int barLength = 20;

        // Calculate current health percentage
        double healthPercent = ( double ) this.health / this.maxHealth;

        // Determine filled and empty portions of the health bar
        int filled = ( int ) ( barLength * healthPercent );
        int empty = barLength - filled;

        StringBuilder bar = new StringBuilder();

        bar.repeat( "█", Math.max( 0, filled ) );
        bar.repeat( " ", Math.max( 0, empty ) );

        // Replace placeholder in ASCII art with the generated health bar
        String result = this.art.replace( "%hhhhhhhhhhhhhhhhhhh", bar.toString() );

        // Print the updated ASCII art
        System.out.println( result );
    }

    // Attacks the player, taking armor into account
    public void attack( Player player ) {
        // Retrieve the player's equipped armor
        Armor armor = ( Armor ) player.getEquipped( EquipmentSlot.ARMOR );
        int damage;

        // Calculate damage based on armor presence and defense value
        if ( armor == null ) {
            damage = this.attackPower;
        }
        else if ( armor.getDefense() < this.attackPower ) {
           damage = this.attackPower - armor.getDefense();
        }
        else {
            damage = 0;
        }

        // Display attack result
        System.out.println( this.getName() + " deals " + damage + " damage to " + player.getName() + "." );

        // Apply damage to the player
        player.takeDamage( damage );
    }

    // Returns the loot dropped by the enemy when defeated
    public List<Item> dropLoot() {
        return this.loot;
    }

    // Creates a copy of the enemy with the same properties
    public Enemy copy() {
        return new Enemy( this.name, this.health, this.attackPower, this.loot, this.art );
    }
}