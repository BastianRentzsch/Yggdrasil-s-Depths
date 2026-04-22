package itemSystem;

// Marks an item as something that can be equipped by the player
public interface Equippable {
    // Returns the equipment slot this item belongs to (e.g. weapon, armor)
    EquipmentSlot getSlot();
}