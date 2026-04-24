// Copyright (c) 2026 Bastian Rentzsch

package itemSystem;

import entitySystem.Entity;

// Represents a consumable item that can be used to heal an entity
public class Consumable extends Item {
    private final Effect effect;

    public Consumable( String name, String description, Effect effect ) {
        super( name, description );
        this.effect = effect;
    }

    // Uses the item to heal the target entity
    public void use( Entity target ) {
        this.effect.apply( target );
    }
}