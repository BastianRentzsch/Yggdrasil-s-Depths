// Copyright (c) 2026 Bastian Rentzsch

package controller;

import model.entity.Enemy;

/**
 * Utility class that provides helper methods for accessing
 * enemy-related data used by the application.
 * <p>
 * This class contains only static methods and cannot be instantiated.
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class EnemyController {

	/**
     * Private constructor to prevent instantiation of this utility class.
     */
	private EnemyController() {
	}
	
	/**
     * Returns the relative file path to the image representing the specified enemy.
     *
     * @param enemy the enemy whose image path should be returned
     * @return the relative path to the enemy's sprite image
     */
	public static String getImagepath(Enemy enemy) {
		return "./res/images/sprites/enemies/" + enemy.getImagename();
	}
	
	/**
     * Returns the maximum health value of the specified enemy.
     *
     * @param enemy the enemy whose maximum health should be retrieved
     * @return the enemy's maximum health
     */
	public static int getMaxHealth(Enemy enemy) {
		return enemy.getMaxHealth();
	}

	/**
     * Returns the current health value of the specified enemy.
     *
     * @param enemy the enemy whose current health should be retrieved
     * @return the enemy's current health
     */
	public static int getHealth(Enemy enemy) {
		return enemy.getHealth();
	}
}
