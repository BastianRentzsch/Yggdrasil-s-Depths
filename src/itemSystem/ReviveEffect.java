// Copyright (c) 2026 Bastian Rentzsch

package itemSystem;

import entitySystem.Entity;

// Implements an effect that revives a dead entity with a percentage of its health
public class ReviveEffect implements Effect {
    private final double percentage;

    public ReviveEffect( double percentage ) {
        this.percentage = percentage;
    }

    // Applies the revive effect to the target entity
    @Override
    public void apply( Entity target ) {
        if ( !target.isAlive() ) {
            target.revive( this.percentage );
            System.out.println( "Revived with " + this.percentage + "% HP" );
        } else {
            System.out.println( "Target is already alive" );
        }
    }
}