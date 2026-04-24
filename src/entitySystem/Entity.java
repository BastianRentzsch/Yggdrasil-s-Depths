// Copyright (c) 2026 Bastian Rentzsch

package entitySystem;

import itemSystem.Inventory;

// Base class for all entities in the game (e.g. player, enemies)
public abstract class Entity {
    protected final String name;
    protected int health;
    protected int maxHealth;
    protected final Inventory inventory = new Inventory();

    public Entity( String name, int health ) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
    }

    // Returns the entity's name
    public String getName() {
        return  this.name;
    }

    // Reduces health when taking damage (cannot go below 0)
    public void takeDamage( int amount ) {
        if ( this.health - amount < 0 ) {
            this.health = 0;
        }
        else {
            this.health -= amount;
        }
    }

    // Heals the entity (cannot exceed maxHealth)
    public void heal( int amount ) {
        if ( this.health + amount > this.maxHealth ) {
            this.health = this.maxHealth;
        }
        else {
            this.health += amount;
        }
    }

    public void revive( double percentage ) {
        this.health = ( int ) ( ( double ) this.maxHealth * percentage );
    }

    // Checks if the entity is still alive
    public boolean isAlive() {
        return this.health > 0;
    }

    // Returns the entity's inventory
    public Inventory getInventory() {
        return this.inventory;
    }
}