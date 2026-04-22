package itemSystem;

import entitySystem.Entity;

// Represents a weapon item that can deal damage and be equipped
public class Weapon extends Item implements Equippable {
    private final int damage;

    public Weapon( String name, String description, int damage ) {
        super( name, description );
        this.damage = damage;
    }

    // Returns the damage value of the weapon
    public int getDamage() {
        return this.damage;
    }

    // Weapons belong to the WEAPON equipment slot
    @Override
    public EquipmentSlot getSlot() {
        return EquipmentSlot.WEAPON;
    }

    // Uses the weapon to deal damage to a target entity
    @Override
    public void use( Entity target ) {
        target.takeDamage( damage );
        System.out.println( target + " took " + damage + " damage." );
    }
}