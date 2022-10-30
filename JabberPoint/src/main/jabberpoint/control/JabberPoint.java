package main.jabberpoint.control;

public class JabberPoint
{
    private static String filename = "demo.xml";

    public static void main(String[] args) {

        if (args.length > 0){
            if (args[0] != null) filename = args[0];
        }

        Controller.getInstance().initialize(new XMLSwingFactory(), filename);
    }
}