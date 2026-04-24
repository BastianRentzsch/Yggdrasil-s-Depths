// Copyright (c) 2026 Bastian Rentzsch

package itemSystem;

import entitySystem.Entity;

// Represents an effect that can be applied to an entity target
public interface Effect {
    void apply( Entity target );
}