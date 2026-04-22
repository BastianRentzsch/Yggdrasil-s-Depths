package itemSystem;

import entitySystem.Entity;

// Represents an armor item that can be equipped and provides defense
public class Armor extends Item implements Equippable {
    private final int defense;

    public Armor( String name, String description, int defense ) {
        super( name, description );
        this.defense = defense;
    }

    // Returns the defense value provided by this armor
    public int getDefense() {
        return defense;
    }

    // Armor belongs to the ARMOR equipment slot
    @Override
    public EquipmentSlot getSlot() {
        return EquipmentSlot.ARMOR;
    }

    // Armor does not have a direct use action
    @Override
    public void use( Entity target ) {
        // intentionally empty
    }
}