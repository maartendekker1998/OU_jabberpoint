package DesignPatterns.observer;

public class ClockTimer extends Subject
{
    public void tick()
    {
        System.out.println("Tick! Updating observers...");
        //update internal time-keeping state
        super.notifyObservers();
    }

    public int getHour()
    {
        return 24;
    }

    public int getMinute()
    {
        return 60;
    }

    public int getSecond()
    {
        return 60;
    }
}
