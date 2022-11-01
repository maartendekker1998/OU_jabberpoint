package main.jabberpoint.domain.iterators;

/**
 * Part of Iterator Pattern
 * Role: Interface to provide the creation of an concrete iterator
 */
public interface Iterable
{
    /**
     * Creates a new concrete Iterator instance
     * @return Iterator instance
     */
    Iterator createIterator();
}
