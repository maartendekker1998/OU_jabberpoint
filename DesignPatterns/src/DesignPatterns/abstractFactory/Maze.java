package DesignPatterns.abstractFactory;

import java.util.HashMap;
import java.util.Map;

public class Maze
{
    //Not in book but necessary
    private Map<Integer, Room> rooms = new HashMap<>();

    public void addRoom(Room room)
    {
        this.rooms.put(room.getRoomNr(), room);
    }

    public boolean roomNo(int room)
    {
        return this.rooms.containsValue(room);
    }

    public Room getRoom(int n)
    {
        return this.rooms.get(n);
    }
}