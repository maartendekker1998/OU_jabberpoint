package DesignPatterns.StatePateren;

public class TCPListen extends TCPState
{
    static TCPListen instance()
    {
        return new TCPListen();
    }

    @Override
    public void send(TCPConnection tcpConnection)
    {
        //do send things
        System.out.println("doing sending things and changing status to established");
        super.changeState(tcpConnection, TCPEstablished.instance());
    }
}
