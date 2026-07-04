// Copyright (c) 2026 Bastian Rentzsch

package model.items.equipments;

/**
 * Represents the equipment slots where items can be equipped.
 * <p>
 * Each slot defines a specific body or usage position for equippable items,
 * such as weapons or armor pieces.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public enum EquipmentSlot {
	
	/** Slot for head equipment such as helmets or hats. */
    HEADWEAR,
    
    /** Slot for body armor such as chestplates or clothing. */
    ARMOR,
    
    /** Slot for accessories such as rings or amulets. */
    ACCESSORY,
    
    /** Slot for weapons used in combat. */
    WEAPON
}