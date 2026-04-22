package entitySystem;

import itemSystem.Inventory;

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

    public String getName() {
        return  this.name;
    }

    public void takeDamage( int amount ) {
        if (health - amount < 0) {
            health = 0;
        }
        else {
            health -= amount;
        }
    }

    public void heal( int amount ) {
        if ( health + amount > maxHealth ) {
            health = maxHealth;
        }
        else {
            health += amount;
        }
    }

    public boolean isAlive() {
        return health > 0;
    }

    public Inventory getInventory() {
        return inventory;
    }
}