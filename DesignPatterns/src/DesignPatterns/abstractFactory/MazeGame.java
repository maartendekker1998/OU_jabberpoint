package DesignPatterns.abstractFactory;

import DesignPatterns.builder.MazeBuilder;

public class MazeGame
{
    Maze createMaze(MazeFactory factory)
    {
        Maze maze = factory.makeMaze();
        Room r1 = factory.makeRoom(1);
        Room r2 = factory.makeRoom(2);
        Door door = factory.makeDoor(r1, r2);

        maze.addRoom(r1);
        maze.addRoom(r2);

        r1.setSide("North", factory.makeWall());
        r1.setSide("East", door);
        r1.setSide("South", factory.makeWall());
        r1.setSide("West", factory.makeWall());

        r2.setSide("North", factory.makeWall());
        r2.setSide("East", factory.makeWall());
        r2.setSide("South", factory.makeWall());
        r2.setSide("West", door);

        return maze;
    }

    //See Builder pattern, this is not part of the abstract factory
    public Maze createMaze(MazeBuilder builder)
    {
        builder.buildMaze();
        builder.buildRoom(1);
        builder.buildRoom(2);
        builder.buildDoor(1, 2);
        return builder.getMaze();
    }

    public Maze createComplexMaze(MazeBuilder builder)
    {
        builder.buildRoom(1);
        //...
        builder.buildRoom(1001);
        return builder.getMaze();
    }
}
