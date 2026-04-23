package itemSystem;

// Represents an armor item that can be equipped and provides defense
public class Armor extends Item implements Equippable {
    private final int defense;

    public Armor( String name, String description, int defense ) {
        super( name, description );
        this.defense = defense;
    }

    // Returns the defense value provided by this armor
    public int getDefense() {
        return this.defense;
    }

    // Armor belongs to the ARMOR equipment slot
    @Override
    public EquipmentSlot getSlot() {
        return EquipmentSlot.ARMOR;
    }
}