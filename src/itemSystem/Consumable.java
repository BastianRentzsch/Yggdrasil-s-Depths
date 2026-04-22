package itemSystem;

import entitySystem.Entity;

// Represents a consumable item that can be used to heal an entity
public class Consumable extends Item {
    private final int healAmount;

    public Consumable( String name, String description, int healAmount ) {
        super( name, description );
        this.healAmount = healAmount;
    }

    // Uses the item to heal the target entity
    @Override
    public void use( Entity target ) {
        target.heal( healAmount );
        System.out.println( "Healed " + healAmount );
    }
}