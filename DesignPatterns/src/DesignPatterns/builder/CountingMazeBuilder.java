package DesignPatterns.builder;

public class CountingMazeBuilder extends MazeBuilder
{
    private int rooms;
    private int doors;

    CountingMazeBuilder()
    {
        this.rooms = this.doors = 0;
    }

    @Override
    public void buildRoom(int n)
    {
        this.rooms++;
    }

    @Override
    public void buildDoor(int r1, int r2)
    {
        this.doors++;
    }

    void getCounts(int rooms, int doors)
    {
        /*In book c++ style with int reference, Java doesn't support this, in Java this would look like:
        public void getCounts()
        {
            return someObject(this.rooms, this.doors);
        }
        Or separate getters, see below
        */
        rooms = this.rooms;
        doors = this.doors;
    }

    int getRooms()
    {
        return this.rooms;
    }

    int getDoors()
    {
        return this.doors;
    }
}
