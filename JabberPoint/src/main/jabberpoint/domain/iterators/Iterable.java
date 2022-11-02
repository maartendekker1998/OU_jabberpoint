package main.jabberpoint.domain.iterators;

/**
 * Part of Iterator Pattern
 * Role: Aggregate
 */
public interface Iterable
{
    /**
     * Creates a new concrete Iterator instance
     * @return Iterator instance
     */
    Iterator createIterator();
}
