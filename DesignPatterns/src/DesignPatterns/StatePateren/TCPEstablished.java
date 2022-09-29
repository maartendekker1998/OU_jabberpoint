package DesignPatterns.StatePateren;

public class TCPEstablished extends TCPState
{
    static TCPEstablished instance()
    {
        return new TCPEstablished();
    }

    @Override
    public void close(TCPConnection tcpConnection)
    {
        System.out.println("doing closing things and changing status to closed");
        //do close things
        super.changeState(tcpConnection, TCPClosed.instance());
    }

    @Override
    public void ack(TCPConnection tcpConnection)
    {
        System.out.println("doing ack things and changing status to listen");
        //do transmit things
        super.changeState(tcpConnection, TCPListen.instance());
    }
}
