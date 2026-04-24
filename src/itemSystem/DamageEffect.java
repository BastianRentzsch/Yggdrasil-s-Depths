// Copyright (c) 2026 Bastian Rentzsch

package itemSystem;

import entitySystem.Entity;

// Implements an effect that deals damage of a specific type to an entity
public class DamageEffect implements Effect {
    private final int damage;
    private final String type;

    public DamageEffect( int damage, String type ) {
        this.damage = damage;
        this.type = type;
    }

    // Applies the damage effect to the target entity
    @Override
    public void apply( Entity target ) {
        target.takeDamage( this.damage );
        System.out.println( "Dealt " + this.damage + " " + this.type + " damage" );
    }
}