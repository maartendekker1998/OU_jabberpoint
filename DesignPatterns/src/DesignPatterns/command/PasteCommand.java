package DesignPatterns.command;

public class PasteCommand implements Command
{
    private Document document;

    public PasteCommand(Document document)
    {
        this.document = document;
    }

    @Override
    public void execute()
    {
        this.document.paste();
        System.out.println("Paste executed");
    }
}
