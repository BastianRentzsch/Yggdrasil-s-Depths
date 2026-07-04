// Copyright (c) 2026 Bastian Rentzsch

package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import model.entity.Enemy;

/**
 * Utility class responsible for loading enemy data from a serialized file.
 * <p>
 * The enemy data is read from the {@code enemycodex.dat} file and returned as
 * an array of {@link Enemy} objects.
 * </p>
 *
 * @author Bastian Rentzsch
 * @since 2026
 */
public class EnemiesLoader {
	
	/**
     * Prevents instantiation of this utility class.
     */
	private EnemiesLoader() {
	}
	
	/**
     * Loads all enemies from the serialized enemy data file.
     *
     * @return an array containing all loaded {@link Enemy} objects
     * @throws IOException if an I/O error occurs while reading the file
     * @throws ClassNotFoundException if the serialized class cannot be found
     */
	public static Enemy[] load() throws IOException, ClassNotFoundException {
	    FileInputStream fis = new FileInputStream("./res/enemycodex.dat");
	    ObjectInputStream ois = new ObjectInputStream(fis);

		Object obj = ois.readObject();

	    ois.close();

	  	return (Enemy[]) obj;
	}
}
