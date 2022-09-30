package DesignPatterns.builder;

import DesignPatterns.abstractFactory.Maze;

public abstract class MazeBuilder
{
    public void buildMaze(){}
    public void buildRoom(int n) {}
    public void buildDoor(int from, int to) {}
    public Maze getMaze() {return null;}
    MazeBuilder(){}
}
