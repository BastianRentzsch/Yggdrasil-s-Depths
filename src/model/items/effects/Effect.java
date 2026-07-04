// Copyright (c) 2026 Bastian Rentzsch

package model.items.effects;

import model.entity.Entity;

/**
 * Represents an effect that can be applied to an entity.
 * <p>
 * Effects are typically used by items such as consumables to modify
 * an entity's state (e.g., healing, damage, buffs, or debuffs).
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public interface Effect {
	/**
     * Applies this effect to the specified target entity.
     *
     * @param target the entity affected by the effect
     */
	void apply(Entity target);
}
