package DesignPatterns.composite;

import java.util.List;

public class Chasis extends CompositeEquipment
{
    public Chasis(List<Equipment> equipment)
    {
        equipment.forEach(super::add);
    }
}
