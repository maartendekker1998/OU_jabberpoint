package DesignPatterns.abstractFactory.bombed;

import DesignPatterns.abstractFactory.MazeFactory;
import DesignPatterns.abstractFactory.Room;
import DesignPatterns.abstractFactory.Wall;

public class BombedMazeFactory extends MazeFactory
{
    public BombedMazeFactory()
    {
        super();
    }

    @Override
    public Wall makeWall()
    {
        return new BombedWall();
    }

    @Override
    public Room makeRoom(int i)
    {
        return new BombedRoom(i);
    }
}
