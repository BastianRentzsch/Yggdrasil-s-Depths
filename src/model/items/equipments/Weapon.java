// Copyright (c) 2026 Bastian Rentzsch

package model.items.equipments;

import java.io.Serializable;

import model.items.Item;
import model.items.Itemtype;

/**
 * Represents a weapon item that can be equipped by the player.
 * <p>
 * Weapons provide offensive power and are equipped in the weapon slot.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class Weapon extends Item implements Equippable, Serializable {

	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * The damage value dealt by this weapon.
     */
	private final int damage;

	/**
     * Creates a new weapon item with the specified properties and damage value.
     *
     * @param name the name of the weapon
     * @param description the description of the weapon
     * @param imagename the image name associated with the weapon
     * @param type the item type
     * @param damage the damage value dealt by this weapon
     */
	public Weapon(String name, String description, String imagename, Itemtype type, int damage) {
	    super(name, description, imagename, type);
	    this.damage = damage;
	}

	/**
     * Returns the damage value of this weapon.
     *
     * @return the damage value
     */
	public int getDamage() {
	    return this.damage;
	}
	
	/**
     * Returns the equipment slot this weapon belongs to.
     *
     * @return {@link EquipmentSlot#WEAPON}
     */
	@Override
	public EquipmentSlot getSlot() {
	    return EquipmentSlot.WEAPON;
	}
}