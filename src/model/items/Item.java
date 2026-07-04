// Copyright (c) 2026 Bastian Rentzsch

package model.items;

import java.io.Serializable;
import java.util.Objects;

/**
 * Base class for all items in the game.
 * <p>
 * An item represents a collectible or usable object with a name, description,
 * image reference, and type.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class Item implements Serializable {

	/**
     * Serial version UID for serialization.
     */
	private static final long serialVersionUID = 1L;

	/**
     * The display name of the item.
     */
	protected final String name;
	
	/**
     * A textual description of the item.
     */
	protected final String description;
	
	/**
     * The name of the image asset associated with this item.
     */
	protected final String imagename;
	
	/**
     * The type classification of this item.
     */
	protected final Itemtype type;

	/**
     * Creates a new item with the specified properties.
     *
     * @param name the name of the item
     * @param description the description of the item
     * @param imagename the image name associated with the item
     * @param type the type of the item
     */
	public Item(String name, String description, String imagename, Itemtype type) {
		this.name = name;
	    this.description = description;
	    this.imagename = imagename;
	    this.type = type;
	}

	/**
     * Returns the name of the item.
     *
     * @return the item name
     */
	public String getName() {
		return this.name;
	}

	/**
     * Returns the description of the item.
     *
     * @return the item description
     */
	public String getDescription() {
		return this.description;
	}

	/**
     * Returns the image name associated with the item.
     *
     * @return the image name
     */
	public String getImagename() {
		return this.imagename;
	}

	/**
     * Returns the type of the item.
     *
     * @return the item type
     */
	public Itemtype getType() {
		return this.type;
	}

	/**
     * Compares this item to another object for equality.
     * <p>
     * Two items are considered equal if they have the same name.
     * </p>
     *
     * @param o the object to compare with
     * @return {@code true} if the objects are equal, otherwise {@code false}
     */
    @Override
    public boolean equals(Object o) {
    		if (this == o) {
    			return true;
		}
        if (!(o instanceof Item item)) {
			return false;
		}
        return Objects.equals(this.name, item.name);
    }

    /**
     * Returns the hash code of this item based on its name.
     *
     * @return hash code of the item
     */
    @Override
    	public int hashCode() {
    		return Objects.hash(this.name);
    }
}