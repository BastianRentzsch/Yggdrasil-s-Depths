package itemSystem;

import entitySystem.Entity;

public class Armor extends Item implements Equippable {

    private final int defense;

    public Armor( String name, String description, int defense ) {
        super( name, description );
        this.defense = defense;
    }

    public int getDefense() {
        return defense;
    }

    @Override
    public EquipmentSlot getSlot() {
        return EquipmentSlot.ARMOR;
    }

    @Override
    public void use( Entity target ) {
        // meistens leer oder special
    }
}
