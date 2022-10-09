package DesignPatterns.composite;

public class Floppy extends Equipment
{
    int price = 10;

    @Override
    int netPrice()
    {
        return this.price;
    }
}
