// Copyright (c) 2026 Bastian Rentzsch

package model.items.equipments;

import java.io.Serializable;

import model.items.Item;
import model.items.Itemtype;


/**
 * Represents a headwear item that can be equipped by the player.
 * <p>
 * Headwear provides defensive stats and is equipped in the head slot.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class Headwear extends Item implements Equippable, Serializable {

	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * The defense value provided by this headwear.
     */
	private final int defense;

	/**
     * Creates a new headwear item with the specified properties and defense value.
     *
     * @param name the name of the headwear
     * @param description the description of the headwear
     * @param imagename the image name associated with the headwear
     * @param type the item type
     * @param defense the defensive value provided by this headwear
     */
	public Headwear(String name, String description, String imagename, Itemtype type, int defense) {
		super(name, description, imagename, type);
	    this.defense = defense;
	}

	/**
     * Returns the defense value of this headwear.
     *
     * @return the defense value
     */
	public int getDefense() {
	    return this.defense;
	}

	/**
     * Returns the equipment slot this headwear belongs to.
     *
     * @return {@link EquipmentSlot#HEADWEAR}
     */
	@Override
	public EquipmentSlot getSlot() {
	    return EquipmentSlot.HEADWEAR;
	}
}