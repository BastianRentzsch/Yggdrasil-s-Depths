package itemSystem;

import entitySystem.Entity;

// Implements an effect that heals an entity by a fixed amount
public class HealEffect implements Effect {
    private final int amount;

    public HealEffect( int amount ) {
        this.amount = amount;
    }

    // Applies the healing effect to the target entity
    @Override
    public void apply( Entity target ) {
        target.heal( this.amount );
        System.out.println( "Healed " + this.amount );
    }
}