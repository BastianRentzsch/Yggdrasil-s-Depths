// Copyright (c) 2026 Bastian Rentzsch

package model.items.equipments;

import java.io.Serializable;

import model.items.Item;
import model.items.Itemtype;


/**
 * Represents an armor item that can be equipped by the player.
 * <p>
 * Armor provides defensive stats and is equipped in the armor slot.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class Armor extends Item implements Equippable, Serializable {

	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * The defense value provided by this armor.
     */
	private final int defense;

	/**
     * Creates a new armor item with the specified properties and defense value.
     *
     * @param name the name of the armor
     * @param description the description of the armor
     * @param imagename the image name associated with the armor
     * @param type the item type
     * @param defense the defensive value provided by this armor
     */
	public Armor(String name, String description, String imagename, Itemtype type, int defense) {
	    super(name, description, imagename, type);
	    this.defense = defense;
	}

	 /**
     * Returns the defense value of this armor.
     *
     * @return the defense value
     */
	public int getDefense() {
	    return this.defense;
	}

	/**
     * Returns the equipment slot this armor belongs to.
     *
     * @return {@link EquipmentSlot#ARMOR}
     */
	@Override
	public EquipmentSlot getSlot() {
	    return EquipmentSlot.ARMOR;
	}
}