package DesignPatterns.command;

public class OpenCommand implements Command
{
    private Application application;
    private String response;

    public OpenCommand(Application application)
    {
        this.application = application;
    }

    private String askUser()
    {
        return "";
    }

    @Override
    public void execute()
    {
        String name = this.askUser();
        if (!name.isEmpty())
        {
            Document document = new Document(name);
            application.add(document);
            document.open();
        }
        System.out.println("Open executed");
    }
}
