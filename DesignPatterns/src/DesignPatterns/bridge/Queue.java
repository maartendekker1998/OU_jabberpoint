package DesignPatterns.bridge;

class Queue extends Collection
{
    Queue(ListImp listImp)
    {
        super(listImp);
        System.out.println("Queue");
    }
}
