package DesignPatterns.StatePateren;

public abstract class TCPState
{
    public void transmit(TCPConnection tcpConnection) {System.out.println("no implemented");}
    public void activeOpen(TCPConnection tcpConnection) {System.out.println("cant open if already open");}
    public void passiveOpen(TCPConnection tcpConnection) {System.out.println("cant open if already open");}
    public void close(TCPConnection tcpConnection) {System.out.println("cant close if not opened");}
    public void ack(TCPConnection tcpConnection) {System.out.println("cant ack if not opened");}
    public void syn(TCPConnection tcpConnection) {System.out.println("no implemented");}
    public void send(TCPConnection tcpConnection) {System.out.println("cannot send if not opened");}

    void changeState(TCPConnection tcpConnection, TCPState state)
    {
        tcpConnection.changeState(state);
    }
}