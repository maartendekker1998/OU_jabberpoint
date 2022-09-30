package DesignPatterns.abstractFactory.bombed;

import DesignPatterns.abstractFactory.Room;

class BombedRoom extends Room
{
    BombedRoom(int i)
    {
        super(i);
        System.out.println("Bombed room " + i + " made");
    }
}
