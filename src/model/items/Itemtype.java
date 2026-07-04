// Copyright (c) 2026 Bastian Rentzsch

package model.items;

/**
 * Represents the different categories of items available in the game.
 * <p>
 * Each item type defines the slot or usage category an item belongs to,
 * such as consumables or equipment types.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public enum Itemtype {
	
	/** Items that can be consumed to produce an effect (e.g., healing, buffs). */
	CONSUMABLES,
	
	/** Items worn on the head (e.g., helmets, hats). */
	HEADWEAR,
	
	/** Protective body equipment (e.g., armor, chestplates). */
	ARMOR,
	
	/** Miscellaneous equipment such as rings or trinkets. */
	ACCESSORY,
	
	/** Offensive equipment used to deal damage (e.g., swords, bows). */
	WEAPON
}
