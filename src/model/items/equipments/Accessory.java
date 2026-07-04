// Copyright (c) 2026 Bastian Rentzsch

package model.items.equipments;

import java.io.Serializable;

import model.items.Item;
import model.items.Itemtype;

/**
 * Represents an accessory item that can be equipped by the player.
 * <p>
 * Accessories provide defensive stats and are equipped in the accessory slot.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class Accessory extends Item implements Equippable, Serializable {

	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * The defense value provided by this accessory.
     */
	private final int defense;

	/**
     * Creates a new accessory item with the specified properties and defense value.
     *
     * @param name the name of the accessory
     * @param description the description of the accessory
     * @param imagename the image name associated with the accessory
     * @param type the item type
     * @param defense the defensive value provided by this accessory
     */
	public Accessory(String name, String description, String imagename, Itemtype type, int defense) {
	    super(name, description, imagename, type);
	    this.defense = defense;
	}

	/**
     * Returns the defense value of this accessory.
     *
     * @return the defense value
     */
	public int getDefense() {
	    return this.defense;
	}

	/**
     * Returns the equipment slot this accessory belongs to.
     *
     * @return {@link EquipmentSlot#ACCESSORY}
     */
	@Override
	public EquipmentSlot getSlot() {
	    return EquipmentSlot.ACCESSORY;
	}
}