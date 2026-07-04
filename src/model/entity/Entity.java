// Copyright (c) 2026 Bastian Rentzsch

package model.entity;

import java.io.Serializable;

/**
 * Base class for all entities in the game.
 * <p>
 * An entity represents any living object with health, such as the player or enemies.
 * It provides basic functionality for taking damage and healing.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class Entity implements Serializable {

	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * The name of the entity.
     */
	protected final String name;
	
	/**
     * The current health of the entity.
     */
    protected int health;
    
    /**
     * The maximum health of the entity.
     */
    protected int maxHealth;

    /**
     * Creates a new entity with the specified name and initial health.
     *
     * @param name the name of the entity
     * @param health the initial and maximum health value
     */
    public Entity(String name, int health) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
    }

    /**
     * Returns the name of this entity.
     *
     * @return the entity name
     */
    public String getName() {
		return this.name;
	}

    /**
     * Returns the maximum health of this entity.
     *
     * @return the maximum health value
     */
    public int getMaxHealth() {
		return this.maxHealth;
	}

    /**
     * Sets the maximum health of this entity.
     *
     * @param maxHealth the new maximum health value
     */
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	/**
     * Returns the current health of this entity.
     *
     * @return the current health value
     */
	public int getHealth() {
		return this.health;
	}

	/**
     * Sets the current health of this entity.
     *
     * @param health the new health value
     */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
     * Reduces the entity's health by the specified amount.
     * <p>
     * Health will not drop below 0.
     * </p>
     *
     * @param amount the damage amount to apply
     */
    public void takeDamage(int amount) {
    		if (this.health - amount < 0) {
    			this.health = 0;
    		}
        else {
        		this.health -= amount;
        }
    }

    /**
     * Restores health to the entity by the specified amount.
     * <p>
     * Health will not exceed the maximum health value.
     * </p>
     *
     * @param amount the healing amount to apply
     */
    public void heal(int amount) {
    		if (this.health + amount > this.maxHealth) {
    			this.health = this.maxHealth;
        }
        else {
            this.health += amount;
        }
    }
}
