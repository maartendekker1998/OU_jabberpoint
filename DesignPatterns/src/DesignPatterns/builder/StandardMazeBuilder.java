package DesignPatterns.builder;

import DesignPatterns.abstractFactory.Door;
import DesignPatterns.abstractFactory.Maze;
import DesignPatterns.abstractFactory.Room;
import DesignPatterns.abstractFactory.Wall;

public class StandardMazeBuilder extends MazeBuilder
{
    private Maze maze;

    //in book Direction class or whatever it is, here just a string
    private String commonWall(Room r1, Room r2)
    {
        return "";
    }

    StandardMazeBuilder()
    {
        this.maze = null;
    }

    @Override
    public void buildMaze()
    {
        this.maze = new Maze();
    }

    @Override
    public void buildRoom(int n)
    {
        if (!this.maze.roomNo(n))
        {
            Room room = new Room(n);
            room.setSide("North", new Wall());
            room.setSide("South", new Wall());
            room.setSide("East", new Wall());
            room.setSide("West", new Wall());
            this.maze.addRoom(room);
        }
    }

    @Override
    public void buildDoor(int from, int to)
    {
        Room r1 = this.maze.getRoom(from);
        Room r2 = this.maze.getRoom(to);
        Door door = new Door(r1, r2);
        r1.setSide(commonWall(r1, r2), door);
        r2.setSide(commonWall(r2, r1), door);
    }

    @Override
    public Maze getMaze()
    {
        return this.maze;
    }
}