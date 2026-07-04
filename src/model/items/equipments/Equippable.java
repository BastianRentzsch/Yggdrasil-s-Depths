// Copyright (c) 2026 Bastian Rentzsch

package model.items.equipments;

/**
 * Represents an item that can be equipped by the player.
 * <p>
 * Implementing classes define which equipment slot they belong to.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public interface Equippable {
	
	/**
     * Returns the equipment slot associated with this item.
     *
     * @return the {@link EquipmentSlot} this item can be equipped in
     */
	EquipmentSlot getSlot();
}