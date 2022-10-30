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

    public void initialize(ControllerFactory factory, String filename) {
        this.infrastructure = factory.getInfraStructure();
        this.projectorService = factory.getProjectorService();
        this.projectorService.setSlideShow(this.infrastructure.loadFile(filename));
        this.userInterface = factory.getUI();
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

    public void loadFile(String uri) {
        SlideShowComponent slideshow = this.infrastructure.loadFile(uri);
        this.projectorService.setSlideShow(slideshow);
        this.nextSlide();
    }

    public void nextSlide()
    {
        SlideShowComponent slide = this.projectorService.getNextSlide();
        if (slide == null) return;
        this.userInterface.renderSlide(slide);
    }

    public void previousSlide()
    {
        this.userInterface.renderSlide(this.projectorService.getPreviousSlide());
    }

    public void nextContent()
    {
        this.userInterface.renderContent(this.projectorService.nextContent());
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
