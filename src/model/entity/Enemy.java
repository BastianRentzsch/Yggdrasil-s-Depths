// Copyright (c) 2026 Bastian Rentzsch

package model.entity;


/**
 * Represents an enemy entity in the game.
 * <p>
 * Enemies are hostile entities with fixed damage, defense, and an image representation.
 * They can attack the player and are typically used in encounters.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class Enemy extends Entity {

	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * The damage value dealt by this enemy.
     */
    private final int damage;

    /**
     * The defense value of this enemy.
     */
    private final int defense;

    /**
     * The image name associated with this enemy.
     */
    private final String imagename;

    /**
     * Creates a new enemy by copying another enemy.
     *
     * @param other the enemy to copy
     */
	public Enemy(Enemy other) {
		super(other.name, other.maxHealth);
	    this.damage = other.damage;
	    this.defense = other.defense;
	    this.imagename = other.imagename;
	}
	
	/**
     * Creates a new enemy with the specified attributes.
     *
     * @param name the name of the enemy
     * @param health the maximum and initial health of the enemy
     * @param damage the damage dealt by the enemy
     * @param defense the defense value of the enemy
     * @param imagename the image name representing the enemy
     */
	public Enemy(String name, int health, int damage, int defense, String imagename) {
		super(name, health);
		this.damage = damage;
		this.defense = defense;
		this.imagename = imagename;
	}

	/**
     * Returns the defense value of this enemy.
     *
     * @return the defense value
     */
	public int getDefense() {
		return this.defense;
	}

	/**
     * Returns the image name of this enemy.
     *
     * @return the image name
     */
	public String getImagename() {
		return this.imagename;
	}

	/**
     * Attacks the specified player, dealing damage based on defense calculation.
     *
     * @param player the player to attack
     */
    public void attack(Player player) {
        int damage = 0;
        int playerDef = player.getDefense();

        if (playerDef == 0) {
            damage += this.damage;
        }
        else if (playerDef < this.damage) {
           damage += this.damage - playerDef;
        }
        else {
            damage += 0;
        }

        player.takeDamage(damage);
    }
}
