package control;

import domain.SlideShowComponent;
import domain_service.ProjectorService;
import infrastructure.Infrastructure;
import userinterface.UserInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller
{
    private ProjectorService projectorService;
    private Infrastructure infrastructure;
    private UserInterface userInterface;
    private static Controller instance;

    private Controller(){}

    public void initialize(ControllerFactory factory) throws IOException {
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

    private void loadFile(String uri) throws IOException {
        SlideShowComponent slideshow = this.infrastructure.loadFile(uri);
    }

    public void nextSlide()
    {
        this.userInterface.renderSlide(this.projectorService.getNextSlide());
    }

    public void previousSlide()
    {

    }

    public SlideShowComponent nextContent()
    {
        return null;
    }

    public void removeLastContent()
    {

    }

    public void removeAllContent()
    {

    }

    public List<SlideShowComponent> remainingContent()
    {
        return new ArrayList<>();
    }
}
