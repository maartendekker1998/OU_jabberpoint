package main.jabberpoint.control;

import java.io.IOException;

public class JabberPoint
{
    public static void main(String[] args) {
        Controller.getInstance().initialize(new XMLSwingFactory());
    }
}