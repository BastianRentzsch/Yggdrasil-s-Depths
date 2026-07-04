// Copyright (c) 2026 Bastian Rentzsch

package model.items.effects;

import java.io.Serializable;

import model.entity.Entity;

/**
 * An effect that restores health to a target entity.
 * <p>
 * When applied, this effect increases the target's health by a fixed amount.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class HealEffect implements Effect, Serializable {
    
	/**
     * Serial version UID for serialization.
     */
	private static final long serialVersionUID = 1L;
	
	/**
     * The amount of health restored by this effect.
     */
	private final int amount;

	/**
     * Creates a new healing effect with the specified amount.
     *
     * @param amount the amount of health restored when applied
     */
    public HealEffect(int amount) {
    		this.amount = amount;
    }

    /**
     * Applies this healing effect to the specified entity.
     *
     * @param target the entity that will be healed
     */
    @Override
    public void apply(Entity target) {
    		target.heal(this.amount);
    }
}
