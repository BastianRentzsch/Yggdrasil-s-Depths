package entitySystem;

import itemSystem.Inventory;

// Base class for all entities in the game (e.g. player, enemies)
public abstract class Entity {
    protected String name;
    protected int health;
    protected int maxHealth;
    protected Inventory inventory = new Inventory();

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
        if (health - amount < 0) {
            health = 0;
        }
        else {
            health -= amount;
        }
    }

    // Heals the entity (cannot exceed maxHealth)
    public void heal( int amount ) {
        if ( health + amount > maxHealth ) {
            health = maxHealth;
        }
        else {
            health += amount;
        }
    }

    // Checks if the entity is still alive
    public boolean isAlive() {
        return health > 0;
    }

    // Returns the entity's inventory
    public Inventory getInventory() {
        return inventory;
    }
}