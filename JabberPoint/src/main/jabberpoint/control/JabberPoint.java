package main.jabberpoint.control;

public class JabberPoint
{
    /** sets the path to the demo presentation  */
    private static String filepath = "demo.xml";

    /**
     * This is the main function of this application.
     * The program can take one argument being a path to a presentation file.
     * If no argument is given, a default demo presentation will be loaded.
     * @param args
     */
    public static void main(String[] args) {

        if (args.length > 0){
            if (args[0] != null) filepath = args[0];
        }

        Controller.getInstance().initialize(new XMLSwingFactory(), filepath);
    }
}