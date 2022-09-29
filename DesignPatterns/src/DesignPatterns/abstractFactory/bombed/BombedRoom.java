package DesignPatterns.abstractFactory.bombed;

import DesignPatterns.abstractFactory.Room;

public class BombedRoom extends Room
{
    public BombedRoom(int i)
    {
        super(i);
        System.out.println("Bombed room " + i + " made");
    }
}
