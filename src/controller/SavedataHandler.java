// Copyright (c) 2026 Bastian Rentzsch

package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.entity.Player;

/**
 * Utility class for saving and loading player save data.
 * <p>
 * The player object is serialized to and deserialized from the
 * {@code savedata.dat} file located in the {@code res/saves} directory.
 * </p>
 *
 * @author Bastian Rentzsch
 * @since 2026
 */
public class SavedataHandler {

	/**
     * Prevents instantiation of this utility class.
     */
	private SavedataHandler() {
	}
	
	/**
     * Saves the specified player to the save data file.
     *
     * @param player the player to save
     * @throws IOException if an I/O error occurs while writing the save file
     */
	public static void save(Player player) throws IOException {
		File file = new File("./res/saves/savedata.dat");
		file.getParentFile().mkdirs();

		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		oos.writeObject(player);

		oos.close();
	}

	/**
     * Loads the player from the save data file.
     *
     * @return the loaded {@link Player} object
     * @throws IOException if an I/O error occurs while reading the save file
     * @throws ClassNotFoundException if the serialized {@link Player} class
     *         cannot be found
     */
	public static Player load() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("./res/saves/savedata.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);

		Object obj = ois.readObject();

        ois.close();

        return (Player) obj;
	}
}
