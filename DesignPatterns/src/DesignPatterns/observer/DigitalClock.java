package DesignPatterns.observer;

public class DigitalClock implements Observer //Not using 'Widget'
{
    private ClockTimer clockTimer;

    public DigitalClock(ClockTimer clockTimer)
    {
        this.clockTimer = clockTimer;
        this.clockTimer.attach(this);
    }

    public void detach() //Detach function cos Java doesn't support deconstructor
    {
        System.out.println("Digital clock detached...");
        this.clockTimer.detach(this);
    }

    @Override
    public void update(Subject subject)
    {
        if (subject.equals(this.clockTimer))
        {
            this.draw();
        }
    }

    private void draw()
    {
        int hour = this.clockTimer.getHour();
        int minute = this.clockTimer.getMinute();
        int second = this.clockTimer.getSecond();
        System.out.println("Digital clock: h: " + hour + ", m: " + minute + ", s:" + second);
        //do something with this
    }
}
