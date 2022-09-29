package DesignPatterns.StatePateren;

public class TCPClosed extends TCPState
{
    static TCPState instance()
    {
        return new TCPClosed();
    }

    @Override
    public void activeOpen(TCPConnection tcpConnection)
    {
        System.out.println("doing tcp active open things and changing status to established");
        //do tcp things
        super.changeState(tcpConnection, TCPEstablished.instance());
    }

    @Override
    public void passiveOpen(TCPConnection tcpConnection)
    {
        System.out.println("doing tcp passive open things and changing status to established");
        //do tcp things
        super.changeState(tcpConnection, TCPEstablished.instance());
    }
}
