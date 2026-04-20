package itemSystem;

import entitySystem.Entity;

public class Consumable extends Item {

    private final int healAmount;

    public Consumable( String name, String description, int healAmount ) {
        super( name, description );
        this.healAmount = healAmount;
    }

    @Override
    public void use( Entity target ) {
        target.heal( healAmount );
        System.out.println( "Healed " + healAmount );
    }
}