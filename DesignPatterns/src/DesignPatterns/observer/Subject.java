package DesignPatterns.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject
{
    private List<Observer> observers = new ArrayList<>();

    void notifyObservers() //Cannot use 'notify' name due to Java.Object.Notify conflict
    {
        this.observers.forEach(observer -> observer.update(this));
    }

    void attach(Observer observer)
    {
        this.observers.add(observer);
    }

    void detach(Observer observer)
    {
        this.observers.remove(observer);
    }
}
