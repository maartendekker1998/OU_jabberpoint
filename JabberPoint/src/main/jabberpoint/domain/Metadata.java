package main.jabberpoint.domain;

import java.util.Map;

/**
 * Singleton pattern
 * Metadata contains information about the presenter and eventually extra information which can be provided in the slide
 */
public class Metadata {

    private static Metadata instance;

    public Map<String, String> metadata;

    /**
     * Private constructor so the instance can only be createed from the getInstance() function
     */
    private Metadata(){}

    /**
     * Initiates the actual data
     * @param metadata map with key and value entities
     */
    public void initialize(Map<String, String > metadata) {
        this.metadata = metadata;
    }

    /**
     * Gets the singleton instance, it will create a new one if the instance is null
     * @return one songleton instance that is not initiated ever time again
     */
    public static Metadata getInstance()
    {
        if (instance == null)
        {
            instance = new Metadata();
            return instance;
        }
        return instance;
    }
}
