package itemSystem;

import entitySystem.Entity;

public class Weapon extends Item implements Equippable {
    private final int damage;

    public Weapon( String name, String description, int damage ) {
        super( name, description );
        this.damage = damage;
    }

    public int getDamage() {
        return this.damage;
    }

    @Override
    public EquipmentSlot getSlot() {
        return EquipmentSlot.WEAPON;
    }

    @Override
    public void use( Entity target ) {
        target.takeDamage( damage );
        System.out.println( target + " took " + damage + " damage." );
    }
}