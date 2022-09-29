package DesignPatterns.bridge;

public class Stack extends Collection
{
    public Stack(ListImp listImp)
    {
        super(listImp);
        System.out.println("Stack");
    }
}
