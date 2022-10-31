package main.jabberpoint.userinterface.handlers;

/**
 * Part of Abstract Factory Pattern
 * Role: Interface to provide functions for a Concrete EventHandler that will be created by the HandleFactory
 * @see HandlerFactory
 */
public interface EventHandler
{
    /**
     * Setter for transitions
     * @param transitions a value to determine if a slide has transitions
     */
    void setTransitions(boolean transitions);
}
