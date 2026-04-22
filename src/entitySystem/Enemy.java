package entitySystem;

import itemSystem.Armor;
import itemSystem.EquipmentSlot;
import itemSystem.Item;

import java.util.List;

// Represents an enemy entity that can attack the player and drop loot
public class Enemy extends Entity {
    private final int attackPower;
    private final List<Item> loot;

    public Enemy( String name, int health, int attackPower,  List<Item> loot) {
        super( name, health );
        this.attackPower = attackPower;
        this.loot = loot;
    }

    // Attacks the player, taking armor into account
    public void attack( Player player ) {
        Armor armor = ( Armor ) player.getEquipped( EquipmentSlot.ARMOR );
        int damage;
        if ( armor == null ) {
            damage = attackPower;
        }
        else if ( armor.getDefense() < attackPower ) {
           damage = attackPower - armor.getDefense();
        }
        else {
            damage = 0;
        }
        System.out.println( this.getName() + " deals " + damage + " damage to " + player.getName() + "." );
    }

    // Returns the loot dropped by the enemy when defeated
    public List<Item> dropLoot() {
        return this.loot;
    }
}