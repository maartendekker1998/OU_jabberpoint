package DesignPatterns.StatePateren;

public class TCPConnection
{
    private TCPState state;

    public TCPConnection()
    {
        state = TCPClosed.instance();
    }

    public void activeOpen()
    {
        state.activeOpen(this);
    }

    public void passiveOpen()
    {
        state.passiveOpen(this);
    }

    public void close()
    {
        state.close(this);
    }

    public void ack()
    {
        state.ack(this);
    }

    public void syn()
    {
        state.syn(this);
    }

    void changeState(TCPState state)
    {
        this.state = state;
    }
}
