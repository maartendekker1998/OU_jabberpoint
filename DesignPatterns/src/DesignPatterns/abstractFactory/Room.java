package DesignPatterns.abstractFactory;

public class Room
{
    protected Room(int i)
    {
        System.out.println("Room " + i + " made");
    }

    public void setSide(String side, Wall wall)
    {

    }

    public void setSide(String side, Door door)
    {

    }
}