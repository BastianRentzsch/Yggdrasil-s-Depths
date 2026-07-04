// Copyright (c) 2026 Bastian Rentzsch

package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

import model.items.Item;

/**
 * Utility class responsible for loading item data from a serialized file.
 * <p>
 * The item data is read from the {@code itemcodex.dat} file and returned as a
 * map containing item identifiers and their corresponding {@link Item} objects.
 * </p>
 *
 * @author Bastian Rentzsch
 * @since 2026
 */
public class ItemsLoader {

	/**
     * Prevents instantiation of this utility class.
     */
	private ItemsLoader() {
	}

	/**
     * Loads all items from the serialized item data file.
     *
     * @return a map containing item identifiers as keys and {@link Item} objects
     *         as values
     * @throws IOException if an I/O error occurs while reading the file
     * @throws ClassNotFoundException if the serialized class cannot be found
     */
	@SuppressWarnings("unchecked")
	public static Map<String, Item> load() throws IOException, ClassNotFoundException {
	    FileInputStream fis = new FileInputStream("./res/itemcodex.dat");
	    ObjectInputStream ois = new ObjectInputStream(fis);

		Object obj = ois.readObject();

	    ois.close();

	  	return (Map<String, Item>) obj;
	}
}
