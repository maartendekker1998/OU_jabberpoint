package DesignPatterns.abstractFactory;

public class Room
{
    //Not in book but necessary
    private int roomNr;

    public Room(int i)
    {
        this.roomNr = i;
        System.out.println("Room " + i + " made");
    }

    public void setSide(String side, Wall wall)
    {

    }

    public void setSide(String side, Door door)
    {

    }

    int getRoomNr()
    {
        return this.roomNr;
    }
}