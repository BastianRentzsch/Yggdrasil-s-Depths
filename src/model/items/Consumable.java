// Copyright (c) 2026 Bastian Rentzsch

package model.items;

import java.io.Serializable;

import model.entity.Entity;
import model.items.effects.Effect;

/**
 * Represents a consumable item that can be used to apply an effect to an entity.
 * <p>
 * Consumables trigger their associated {@link Effect} when used, such as healing
 * or applying buffs/debuffs to a target entity.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class Consumable extends Item implements Serializable {
	
	/**
     * Serial version UID for serialization.
     */
	private static final long serialVersionUID = 1L;
	
	/**
     * The effect that is applied when this consumable is used.
     */
	private final Effect effect;

	/**
     * Creates a new consumable item with the specified properties and effect.
     *
     * @param name the name of the consumable
     * @param description the description of the consumable
     * @param imagename the image name associated with the consumable
     * @param type the item type
     * @param effect the effect applied when the consumable is used
     */
    public Consumable(String name, String description, String imagename, Itemtype type, Effect effect) {
    		super(name, description, imagename, type);
        this.effect = effect;
    }

    /**
     * Uses this consumable on the specified entity, applying its effect.
     *
     * @param target the entity that will be affected by this consumable
     */
    public void use(Entity target) {
    		this.effect.apply(target);
    }
}
