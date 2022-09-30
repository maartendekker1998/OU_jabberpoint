package DesignPatterns.abstractFactory;

public abstract class MazeFactory
{
    protected MazeFactory(){}

    Maze makeMaze()
    {
        return new Maze();
    }

    public Wall makeWall()
    {
        return new Wall();
    }

    public Room makeRoom(int i)
    {
        return new Room(i);
    }

    Door makeDoor(Room r1, Room r2)
    {
        return new Door(r1, r2);
    }
}