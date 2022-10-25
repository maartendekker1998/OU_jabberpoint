package main.jabberpoint.domain;

import main.jabberpoint.control.ControllerFactory;

import java.util.Map;

public class Metadata {

    private static Metadata instance;

    public Map<String, String> metadata;

    private Metadata(){}

    public void initialize(Map<String, String > metadata) {
        this.metadata = metadata;
    }

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
