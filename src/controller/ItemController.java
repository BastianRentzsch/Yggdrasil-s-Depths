// Copyright (c) 2026 Bastian Rentzsch

package controller;

import model.items.Consumable;
import model.items.Item;
import model.items.equipments.Accessory;
import model.items.equipments.Armor;
import model.items.equipments.Headwear;
import model.items.equipments.Weapon;

/**
 * Utility controller class that provides static methods for accessing
 * information about {@link Item} objects.
 * <p>
 * This class offers helper methods for retrieving item properties such as
 * the image path, display name, combat statistic, and description.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class ItemController {

	/**
     * Prevents instantiation of this utility class.
     */
	private ItemController() {
	}
	
	/**
     * Returns the relative image path for the specified item.
     * <p>
     * The path is determined based on the concrete item type
     * (e.g. weapon, armor, consumable).
     * </p>
     *
     * @param item the item
     * @return the relative path to the item's image
     */
	public static String getImagePath(Item item) {
		String dir = "";
		if (item instanceof Headwear) {
			dir = "./res/images/items/headwear/";
		}
		else if (item instanceof Armor) {
			dir = "./res/images/items/armor/";
		}
		else if (item instanceof Accessory) {
			dir = "./res/images/items/accessory/";
		}
		else if (item instanceof Weapon) {
			dir = "./res/images/items/weapon/";
		}
		else if (item instanceof Consumable) {
			dir = "./res/images/items/consumables/";
		}
		else {
			dir = "./res/images/items/";
		}
		
		return dir + item.getImagename();
	}
	
	/**
     * Returns the display name of the specified item.
     *
     * @param item the item
     * @return the item's name
     */
	public static String getName(Item item) {
		return item.getName();
	}
	
	/**
     * Returns the primary combat statistic of the specified item.
     * <p>
     * For weapons, this method returns the damage value.
     * For headwear, armor, and accessories, it returns the defense value.
     * For all other item types, {@code 0} is returned.
     * </p>
     *
     * @param item the item
     * @return the item's primary combat statistic, or {@code 0} if none exists
     */
	public static int getStat(Item item) {
		int stat = 0;
		
		if (item instanceof Headwear) {
			stat = ((Headwear) item).getDefense();
		}
		else if (item instanceof Armor) {
			stat = ((Armor) item).getDefense();
		}
		else if (item instanceof Accessory) {
			stat = ((Accessory) item).getDefense();
		}
		else if (item instanceof Weapon) {
			stat = ((Weapon) item).getDamage();
		}
		
		return stat;
	}
	
	/**
     * Returns the description of the specified item.
     *
     * @param item the item
     * @return the item's description
     */
	public static String getDescription(Item item) {
		return item.getDescription();
	}
}
