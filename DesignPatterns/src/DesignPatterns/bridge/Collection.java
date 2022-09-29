package DesignPatterns.bridge;

abstract class Collection
{
    private ListImp listImp;

    Collection(ListImp listImp)
    {
        this.listImp = listImp;
    }
}
