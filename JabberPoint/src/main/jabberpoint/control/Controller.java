package control;

import domain.SlideShowComponent;
import domain_service.ProjectorService;
import userinterface.UserInterface;
import java.util.ArrayList;
import java.util.List;

public class Controller
{
    private ProjectorService projectorService;
    //infrastructure omitted
    private UserInterface userInterface;
    private static Controller instance;

    private Controller(){}

    public void initialize(ControllerFactory factory)
    {
        //this.infraStructure = factory.getInfraStructure();
        this.userInterface = factory.getUI();
        this.projectorService = factory.getProjectorService();
        //this.projectorService.setSlideShow(this.infraStructure.loadFile());
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

    private void loadFile(String url)
    {
        //omitted
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
