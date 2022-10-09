package DesignPatterns.composite;

import java.util.ArrayList;
import java.util.List;

public abstract class CompositeEquipment extends Equipment
{
    private List<Equipment> equipmentList = new ArrayList<>();

    public void add(Equipment e)
    {
        this.equipmentList.add(e);
    }

    @Override
    int netPrice()
    {
        int i = 0;
        for (Equipment e : equipmentList)
        {
            i+= e.netPrice();
        }
        return i;
    }
}
