// Copyright (c) 2026 Bastian Rentzsch

package model.items;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a player's inventory that stores items and their quantities.
 * <p>
 * The inventory manages item stacking, removal, lookup, and filtering by type.
 * Each item is stored together with an associated amount.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class Inventory implements Serializable {

    /**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Internal storage of items mapped to their quantities.
     */
	private final Map<Item, Integer> items = new HashMap<>();

	/**
     * Adds a specified amount of an item to the inventory.
     *
     * @param item the item to add
     * @param amount the number of items to add
     */
    public void addItem(Item item, int amount) {
    		if (amount <= 0 || item == null) {
    			return;
		}
    		this.items.put(item, this.items.getOrDefault(item, 0) + amount);
    }

    /**
     * Removes a specified amount of an item from the inventory.
     *
     * @param item the item to remove
     * @param amount the number of items to remove
     * @return {@code true} if the removal was successful, {@code false} otherwise
     */
    public boolean removeItem(Item item, int amount) {
    		if (amount <= 0 || item == null) {
    			return false;
		}

        int current = this.items.getOrDefault(item, 0);
        if (current < amount) {
        		return false;
		}

        if (current == amount) {
        		this.items.remove(item);
        } 
        else {
        		this.items.put(item, current - amount);
        }
        return true;
    }

    /**
     * Returns the quantity of a specific item in the inventory.
     *
     * @param item the item to check
     * @return the number of items stored, or {@code 0} if not present
     */
    public int getAmount(Item item) {
        return this.items.getOrDefault(item, 0);
    }

    /**
     * Checks whether the inventory contains a specific item.
     *
     * @param item the item to check
     * @return {@code true} if the item exists in the inventory
     */
    public boolean contains(Item item) {
    		return this.items.containsKey(item);
    }

    /**
     * Returns the internal map of items and their quantities.
     *
     * @return the item map
     */
    public Map<Item, Integer> getItems() {
        return this.items;
    }

    /**
     * Finds an item entry by its name (case-insensitive).
     *
     * @param name the name of the item to search for
     * @return the matching map entry, or {@code null} if not found
     */
    public Map.Entry<Item, Integer> getItem(String name) {
    		for (Map.Entry<Item, Integer> entry : this.items.entrySet()) {
    			if (entry.getKey().getName().equalsIgnoreCase(name)) {
    				return entry;
    			}
    		}

		return null;
    }

    /**
     * Returns all items of a specific type.
     *
     * @param type the item type to filter by
     * @return a list of items matching the given type
     */
    public List<Item> getEquipments(Itemtype type) {
    		List<Item> equipmemts = new ArrayList<>();

    		for (Map.Entry<Item, Integer> entry : this.items.entrySet()) {
    			if (entry.getKey().getType() == type) {
    				equipmemts.add(entry.getKey());
    			}
    		}

		return equipmemts;
    }
}