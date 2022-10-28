package main.jabberpoint.control;

import main.jabberpoint.domain.SlideShowComponent;
import main.jabberpoint.domain_service.ProjectorService;
import main.jabberpoint.infrastructure.Infrastructure;
import main.jabberpoint.userinterface.UserInterface;

public class Controller
{
    private ProjectorService projectorService;
    private Infrastructure infrastructure;
    private UserInterface userInterface;
    private static Controller instance;

    private Controller(){}

    public void initialize(ControllerFactory factory) {
        this.infrastructure = factory.getInfraStructure();
        this.userInterface = factory.getUI();
        this.projectorService = factory.getProjectorService();
        this.projectorService.setSlideShow(this.infrastructure.loadFile("test.xml"));
        this.nextSlide();
    }

    public static Controller getInstance()
    {
        if (instance == null)
        {
            instance = new Controller();
            return instance;
        }
        return instance;
    }

    private void loadFile(String uri) {
        SlideShowComponent slideshow = this.infrastructure.loadFile(uri);
    }

    public void nextSlide()
    {
        this.userInterface.renderSlide(this.projectorService.getNextSlide());
    }

    public void previousSlide()
    {
        this.userInterface.renderSlide(this.projectorService.getPreviousSlide());
    }

    public SlideShowComponent nextContent()
    {
        this.userInterface.renderContent(this.projectorService.nextContent());
        return null;
    }

    public void removeLastContent()
    {
        SlideShowComponent content = this.projectorService.removeLastContent();
        if (content == null) return;
        this.userInterface.removeLastContent(content);
    }

    public void removeAllContent()
    {
        this.projectorService.removeAllContent();
        this.userInterface.removeAllContent();
    }

    public void remainingContent()
    {
        this.userInterface.renderContent(this.projectorService.remainingContent());
    }
}
